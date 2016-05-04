package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Wijn;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;

/**
 * Servlet implementation class WijnDetailServlet
 */
@WebServlet("/wijnDetail.htm")
public class WijnDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/wijnDetail.jsp";
	private static final String REDIRECT_URL = "%s/index.htm";

	private final transient WijnService wijnService = new WijnService();
	private final transient LandService landService = new LandService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("landen", landService.findAll());

		if (request.getParameter("id") != null) {
			try {
				Wijn wijn = wijnService.read(Long.parseLong(request.getParameter("id")));
				if (wijn != null) {
					request.setAttribute("wijn", wijn);
				} else {
					request.setAttribute("fouten",
							Collections.singletonMap("id", "Geen wijn gevonden met opgegeven id"));
				}
			} catch (NumberFormatException ex) {
				request.setAttribute("fouten", Collections.singletonMap("id", "Ongeldig id opgegeven"));
			}
		} else {
			request.setAttribute("fouten",
					Collections.singletonMap("id", "Gelieve een wijn te selecteren via het navigatiemenu"));
		}

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();

		if (request.getParameter("id") != null) {

			try {
				
				long id = Long.parseLong(request.getParameter("id"));
				int aantalFlessen = Integer.parseInt(request.getParameter("aantalFlessen"));

				if (aantalFlessen >= 1) {
					
					addOrderToMandje(request, id, aantalFlessen);
					response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));

				} else {
					fouten.put("input", "Aantal te bestellen flessen moet minstens één zijn");
				}
			} catch (NumberFormatException ex) {
				fouten.put("input", "Ongeldige input doorgegeven. [Your IP has been logged]");
			}
		} else {
			fouten.put("id", "Ongeldig id opgegeven");
		}
		
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	private void addOrderToMandje(HttpServletRequest request, Long id, Integer aantal) {

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje");
		if (mandje == null) {
			mandje = new HashMap<>();
		}
		Integer current = mandje.putIfAbsent(id, aantal);
		if (current != null) {
			mandje.put(id, aantal + current);
		}
		session.setAttribute("mandje", mandje);

	}

}
