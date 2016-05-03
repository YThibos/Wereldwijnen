package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Land;
import be.vdab.entities.Soort;
import be.vdab.services.LandService;
import be.vdab.services.SoortService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	
	// SERVICES
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("landen", landService.findAll());
		
		if (request.getParameter("soortid") != null) {
			Soort gevraagdeSoort = soortService.read(Long.parseLong(request.getParameter("soortid")));
			
		}
		
		// Als user soorten v/e land heeft opgevraagd, fetch soorten en zet als attribute
		if (request.getParameter("landid") != null) {
			Land gevraagdLand = landService.read(Long.parseLong(request.getParameter("landid")));
			request.setAttribute("gevraagdLand", gevraagdLand);
			request.setAttribute("soorten", soortService.findByLand(gevraagdLand));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
