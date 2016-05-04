package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;

/**
 * Servlet implementation class SuccesServlet
 */
@WebServlet("/succes.htm")
public class SuccesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/succes.jsp";
	
	private final transient LandService landService = new LandService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("landen", landService.findAll());
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
