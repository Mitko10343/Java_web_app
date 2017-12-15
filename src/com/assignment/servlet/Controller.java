/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */

package com.assignment.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.assignment.commandExceptions.CommandCreationException;
import com.assignment.commands.Command;
import com.assignment.commands.CommandFactory;



/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns={"/Controller"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	//Controller Constructor
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }


    //Get http GET request
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

	
	//Get http POST request
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}	
	
	
	//Method to process HTTP GET and Post Requests
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {

		String forwardToJsp = null;	
		//get the value in the input with name action 1 and assign it to a string
		String action = request.getParameter("action1");
		
		//get a Command Factory object and initialise a Command Object
		CommandFactory factory = CommandFactory.getInstance();
		Command command = null;
		
		//Try to create a comand from command factory and assign it to the command object
		//Then try to execture this command 
		try {
			command = factory.createCommand(action);
			forwardToJsp = command.execute(request, response);
		} catch (CommandCreationException e) {			
			e.printStackTrace();
			//If the try fails forward to the error pages
			forwardToJsp = "/errorPage.jsp";
		}		
		forwardToPage(request, response, forwardToJsp);
	}
	
	
	//Forward to servlet page
	private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page){
		
		//Get the request dispatcher object and forward the request to the appropriate JSP page...
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}