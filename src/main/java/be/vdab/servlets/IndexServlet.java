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
import be.vdab.services.WijnService;

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
	private final transient WijnService wijnService = new WijnService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("landen", landService.findAll());
		
		// Check landid gegeven. Als handmatig landid en soortid beiden ingevoerd -> enkel landid telt.
		if (request.getParameter("landid") != null) {
			try {
				// Set request attributen adhv landid parameter
				setLandidAttributen(request, Long.parseLong(request.getParameter("landid")));
			}
			catch (NumberFormatException ex) {
				request.setAttribute("idfout", "Ongeldig id meegegeven voor land");
			}
		}
		else if (request.getParameter("soortid") != null) {
			try {
				// Set request attributen adhv soortid parameter
				setSoortidAttributen(request, Long.parseLong(request.getParameter("soortid")));
				Soort gevraagdeSoort = (Soort) request.getAttribute("gevraagdeSoort");
				if (gevraagdeSoort != null) {
					setLandidAttributen(request, gevraagdeSoort.getLand().getId());
				}
			}
			catch (NumberFormatException ex) {
				request.setAttribute("idfout", "Ongeldig id meegegeven voor wijnsoort");
			}
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	private void setLandidAttributen(HttpServletRequest request, long landid) {
		Land gevraagdLand = landService.read(landid);
		if (gevraagdLand != null) {
			request.setAttribute("gevraagdLand", gevraagdLand);
			request.setAttribute("soorten", soortService.findByLand(gevraagdLand));
		}
		else {
			request.setAttribute("idfout", "Geen land gevonden met opgegeven id");
		}
	}
	
	private void setSoortidAttributen(HttpServletRequest request, long soortid) {
		Soort gevraagdeSoort = soortService.read(soortid);
		if (gevraagdeSoort != null) {
			request.setAttribute("gevraagdeSoort", gevraagdeSoort);
			request.setAttribute("wijnen", wijnService.findBySoort(gevraagdeSoort));
		}
		else {
			request.setAttribute("idfout", "Geen wijnsoort gevonden met opgegeven id");
		}
	}

}
