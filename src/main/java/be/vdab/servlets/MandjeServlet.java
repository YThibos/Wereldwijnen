package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Bestelbon;
import be.vdab.enums.Bestelwijze;
import be.vdab.services.BestelbonService;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;
import be.vdab.util.InputValidator;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

/**
 * Servlet implementation class MandjeServlet
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "%s/succes.htm?id=%d";

	private final transient LandService landService = new LandService();
	private final transient WijnService wijnService = new WijnService();
	private final transient BestelbonService bestelbonService = new BestelbonService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("landen", landService.findAll());
		
		Map<Long, Integer> mandje = fetchMandje(request);

		if (mandje != null) {

			Set<Bestelbonlijn> bestellijnen = createBestelbonlijnSet(request, mandje);

			request.setAttribute("bestellijnen", bestellijnen);
			request.setAttribute("bestelwijzen", Bestelwijze.getValuesList());
		}

		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();
		Map<Long, Integer> mandje = fetchMandje(request);
		
		checkInput(request, fouten);

		// Haal adres uit (gecheckte) parameters en steek in adres object.
		Adres adres;
		try {
			adres = new Adres(request.getParameter("straat"), request.getParameter("huisnummer"),
					request.getParameter("postcode"), request.getParameter("gemeente"));
		} catch (IllegalArgumentException ex) {
			// Ongeldige input meegegeven voor adres, ga terug naar doPost
			adres = null;
			request.setAttribute("fouten", fouten);
			doGet(request, response);
		}
		
		Bestelbon bestelbon = null;
		
		// Stel bestelbon samen op basis van gegevens in mandje
		if (mandje != null && fouten.isEmpty()) {
			Set<Bestelbonlijn> bestelbonlijnen = new HashSet<>();

			for (Entry<Long, Integer> entry : mandje.entrySet()) {
				bestelbonlijnen.add(
						new Bestelbonlijn(entry.getValue().intValue(), 
												wijnService.read(entry.getKey()))
						);
			}
		
			bestelbon = extractBestelbonFromParameters(request);
			bestelbon.setAdres(adres);
			bestelbon.setBestelbonlijnen(bestelbonlijnen);
		}

		// Als nog steeds geen fouten, persist bestelbon
		if (fouten.isEmpty()) {
			
			// Verhoog per wijn het aantal in bestelling
			for (Bestelbonlijn bestelbonlijn : bestelbon.getBestelbonlijnen()) {
				bestelbonlijn.getWijn().addInBestelling(bestelbonlijn.getAantal());
			}
			
			Bestelbon updatedBestelbon = bestelbonService.tryCreate(bestelbon);
			if (updatedBestelbon != null) {
				request.getSession().invalidate();
				response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath(), updatedBestelbon.getId()));
			}
			else {
				fouten.put("mandje", "Fout bij het aanmaken van de bestelbon");
			}
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			doGet(request, response);
		}
	}


	private void checkInput(HttpServletRequest request, Map<String, String> fouten) {

		// .. kan dit anders .. ?
		try {
			InputValidator.checkNotNullOrEmpty(request.getParameter("naam"));
		} catch (IllegalArgumentException ex) {
			fouten.put("naam", ex.getMessage());
		}
		try {
			InputValidator.checkNotNullOrEmpty(request.getParameter("straat"));
		} catch (IllegalArgumentException ex) {
			fouten.put("straat", ex.getMessage());
		}
		try {
			InputValidator.checkNotNullOrEmpty(request.getParameter("huisnummer"));
		} catch (IllegalArgumentException ex) {
			fouten.put("huisnummer", ex.getMessage());
		}
		try {
			InputValidator.checkNotNullOrEmpty(request.getParameter("postcode"));
		} catch (IllegalArgumentException ex) {
			fouten.put("postcode", ex.getMessage());
		}
		try {
			InputValidator.checkNotNullOrEmpty(request.getParameter("gemeente"));
		} catch (IllegalArgumentException ex) {
			fouten.put("gemeente", ex.getMessage());
		}
		try {
			String bestelwijze = request.getParameter("bestelwijze");
			InputValidator.checkNotNullOrEmpty(bestelwijze);
			if (! Bestelwijze.getValuesList()
					.contains(Bestelwijze.valueOf(bestelwijze))) {
				fouten.put("bestelwijze", "Ongeldige bestelwijze meegegeven.");
			}
		} catch (IllegalArgumentException ex) {
			fouten.put("bestelwijze", "Opgegeven string mag niet null of leeg zijn.");
		}

	}

	private Map<Long, Integer> fetchMandje(HttpServletRequest request) {

		if (request.getSession(false) != null) {

			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) request.getSession(false).getAttribute("mandje");

			return mandje;
		} else {
			request.setAttribute("fouten",
					Collections.singletonMap("mandje", "Mandje werd niet gevonden, sessie verlopen"));
			return null;
		}

	}

	private Bestelbon extractBestelbonFromParameters(HttpServletRequest request) {
		
		Bestelwijze bestelwijze = Bestelwijze.valueOf(request.getParameter("bestelwijze").toUpperCase());
		String naam = request.getParameter("naam");
		Adres dummy = new Adres("a", "b", "c", "d");
		Set<Bestelbonlijn> dummyset = new HashSet<>();
		Bestelbon bestelbon = new Bestelbon(bestelwijze, naam, dummy, dummyset);
		
		return bestelbon;
	}
	
	private Set<Bestelbonlijn> createBestelbonlijnSet(HttpServletRequest request, Map<Long, Integer> mandje) {
		Set<Bestelbonlijn> bestellijnen = new HashSet<>();
		if (mandje != null) {

			for (Entry<Long, Integer> entry : mandje.entrySet()) {
				bestellijnen.add(new Bestelbonlijn(entry.getValue().intValue(), wijnService.read(entry.getKey())));
			}
			
		}
		return bestellijnen;
	}
}
