/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */

package com.assignment.gamesService;

import java.util.ArrayList;

import com.assignment.dao.*;
import com.assignment.games.dao.Games;

//games services class
public class gameService {
	//get an object of the database
	static dao dao = new dao();
	//make an array list
	static ArrayList<Games> games = new ArrayList<Games>();
	
	//searhcing for games method
	public ArrayList<Games> searchForGame(String GameName)
	{		
		//try to call the getGames method from the dao and assign the results to the array list
		try{
			games= dao.getGames(GameName);
		}catch(Exception e)//if anything goes wrong print an error message to the console
		{
			e.printStackTrace();
			System.out.println("9999999");
		}
		return games;//return the array list
	}
	
	//method to list all the games from the database
	public ArrayList<Games> listAllGames()
	{
		//try to get all the games from the database and assign the result to an array list
		try{
			games = dao.listAllGames();
			
		}catch(Exception e)//if something goes wrong print an error message
		{
			e.printStackTrace();
			System.out.println("Error Listing All The games");
		}
		return games;//return the array list 
	}
	
	//array list to update a game rating
	public ArrayList<Games> AddRating(String gName,int rate)
	{
		//try to update the rating of games 
		try{
			games = dao.AddRating(gName,rate);
		}catch(Exception e)//if something goes wrong print an error message to console
		{
			e.printStackTrace();
			System.out.println("Error Listing Adding A rating");
		}
		return games;//return the array list
	}
}
