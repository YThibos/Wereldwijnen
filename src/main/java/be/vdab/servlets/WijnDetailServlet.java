package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	private final transient WijnService wijnService = new WijnService();
	private final transient LandService landService = new LandService();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("landen", landService.findAll());
		
		if (request.getParameter("id") != null) {
			try {
				Wijn wijn = wijnService.read(Long.parseLong(request.getParameter("id")));
				if (wijn != null) {
					request.setAttribute("wijn", wijn);
				}
				else {
					request.setAttribute("fouten", Collections.singletonMap("id", "Geen wijn gevonden met opgegeven id"));
				}
			}
			catch (NumberFormatException ex) {
				request.setAttribute("fouten", Collections.singletonMap("id", "Ongeldig id opgegeven"));
			}
		}
		else {
			request.setAttribute("fouten", Collections.singletonMap("id", "Gelieve een wijn te selecteren via het navigatiemenu"));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
