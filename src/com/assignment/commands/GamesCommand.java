package com.assignment.commands;

import java.util.ArrayList;
/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment.games.dao.Games;
import com.assignment.gamesService.*;

public class GamesCommand implements Command {

	//Create variable that will be used to check what button was clicked by the user
	private static final String GAMES = "oneGame";
	private static final String LIST = "listGames";
	private static final String RATING = "AddRating";
	
	//method to execute a command
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
		//create an array list
		ArrayList<Games> games = new ArrayList<Games>();
		//create a game service object
		gameService gameService = new gameService();
		String forwardToJsp = "";		
		//get the value from input with name action and assign it to a string
		String action = request.getParameter("action");
		
		//if action = one GAMES then continue
		if(action.equalsIgnoreCase(GAMES))
		{
			//get search input from input field called games
			String gName = request.getParameter("games");
			
			//if the search value is empty continue else re-direct to error page
			if (gName != null){
				 //call the search for game method from game service and assign the return to games array list
				 games = gameService.searchForGame(gName);
				
				//if the search returns any results then continue else re-direct to failed search page
				if (games != null){
					//get a session
					HttpSession session = request.getSession();
					//get the session ID
					String clientSessionId = session.getId();
					//set a session attribute 
					session.setAttribute("SearchSessionID", clientSessionId);
					
					//pass the array list of results to the searchSuccess.jsp page
					session.setAttribute("results",games);
					forwardToJsp = "/searchSuccess.jsp";		
				
				}
				else{
					forwardToJsp = "/failedSearch.jsp";	
				}
			}
			else{
				forwardToJsp = "/failedSearch.jsp";
			}
		}
		//If the action is equal to LIST then display all the games in the database
		else if(action.equalsIgnoreCase(LIST))
		{
			//get the list of all the games in database and assign them to array list
			games = gameService.listAllGames();
			HttpSession session = request.getSession();
			String clientSessionId = session.getId();
			session.setAttribute("ID", clientSessionId);
			
			session.setAttribute("gName", games);
			forwardToJsp = "/ListGames.jsp";
			
			//if games database is empty forward user to failedSearch.jsp page
			if(games == null)
			{
				forwardToJsp = "/failedSearch.jsp";	
			}
		}
		//If action is equal to RATING then update the game rating
		else if(action.equalsIgnoreCase(RATING))
		{
			//get the rating the user inputed and parse it into a string
			int rate = Integer.parseInt(request.getParameter("rating"));
			//get the name of the game the user is rating
			String gameName = request.getParameter("gameName");
			//update the rating of the game and return the update list of the games
			//with the new rating
			games = gameService.AddRating(gameName,rate);
			
			HttpSession session = request.getSession();
			String clientSessionId = session.getId();
			session.setAttribute("ID", clientSessionId);
			//pass the updated games list to the ListGames.jsp page and forward the user to the page
			session.setAttribute("gName", games);
			forwardToJsp = "/ListGames.jsp";
			
		}
		//if the user clicked something else then forward them to error page
		else {
			System.out.println("666");
			forwardToJsp = "/failedSearch.jsp";	
		}
		//return the forward to JSP string to the controller class
		return forwardToJsp;
	}

}