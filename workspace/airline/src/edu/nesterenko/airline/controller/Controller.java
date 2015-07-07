package edu.nesterenko.airline.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.command.Command;
import edu.nesterenko.airline.command.CommandFactory;
import edu.nesterenko.airline.exception.LogicalException;



/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private final static Logger LOG = Logger.getLogger(Controller.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Command currentCommand = CommandFactory.determineCommand(request.getParameter("command"));
			String jspPath = currentCommand.execute(request);
			request.getRequestDispatcher(jspPath).forward(request, response);
		} catch (LogicalException e) {
			LOG.error(e);
		}
	}

}
