package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;
import be.vdab.services.WijnService;
import be.vdab.valueobjects.Bestelbonlijn;

/**
 * Servlet implementation class MandjeServlet
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	
	private final transient LandService landService = new LandService();
	private final transient WijnService wijnService = new WijnService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("landen", landService.findAll());
		
		@SuppressWarnings("unchecked")
		Map<Long, Integer> mandje = (Map<Long, Integer>) request.getSession(false).getAttribute("mandje");
		
		if (mandje != null) {
			
			List<Bestelbonlijn> bestellijnen = new LinkedList<>();
			
			for (Entry<Long, Integer> entry : mandje.entrySet()) {
				bestellijnen.add(new Bestelbonlijn(entry.getValue().intValue(), wijnService.read(entry.getKey())));
				System.out.println("AANTAL FLESSEN UIT MANDJE: " + entry.getKey().intValue());
			}
			
			request.setAttribute("bestellijnen", bestellijnen);
			
		}
		else {
			request.setAttribute("fouten", Collections.singletonMap("mandje", "Mandje werd niet gevonden, sessie verlopen"));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
