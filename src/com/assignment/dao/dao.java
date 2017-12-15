/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */

package com.assignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.assignment.exceptions.daoException;
import com.assignment.games.dao.Games;


public class dao {

	
	//Connect to the database and return a connection
    protected Connection getConnection() throws daoException {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/MySql";
        String username = "root";
        String password = "";
        Connection con = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2);
        }
        return con;//return a connections
    }
    
    //free the connection from database
    protected void freeConnection(Connection con) throws daoException {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
    
    //method that gets the game the user searched for from and returns all the results in an array list
    public ArrayList<Games> getGames(String gName) throws SQLException
    {
    	ArrayList<Games> games = new ArrayList<Games>();//make an array list
    	
    	Connection con = this.getConnection();//get a connection to database
    	Statement stmt = con.createStatement() ;//create a statement
    	//get a result set from the database
    	ResultSet rs = stmt.executeQuery( "SELECT * FROM GAMES where gamename = '"+gName+"'") ;
    	//while the result set is not empty read the next line
    	while(rs.next())
    	{
    		//assign the columns from the result set to variables
	    	int id = rs.getInt(1);
	    	String name = rs.getString(2);
	    	int rating = rs.getInt(3);
	    	String review = rs.getString(4);
	    	//cretate a game object and add it to array list
	    	Games g = new Games(id,name,rating,review);
	    	games.add(g);
	    	System.out.println(g);
    	}
    	
    	rs.close();//close result set
    	return games;//return array list
    }
    
    //method that lists all the games from the database
    public ArrayList<Games> listAllGames() throws SQLException
    {
    	ArrayList<Games> games = new ArrayList<Games>();//make array list
    	//get a connection
    	Connection con = this.getConnection();
    	Statement stmt = con.createStatement() ;//create a connection statement
    	ResultSet rs = stmt.executeQuery( "SELECT * FROM GAMES") ;//get a result set of all games in database
    	
    	//while result set is not empty make a game object and add it to array list
    	while(rs.next())
    	{
	    	int id = rs.getInt(1);
	    	String name = rs.getString(2);
	    	int rating = rs.getInt(3);
	    	String review = rs.getString(4);
	    	
	    	Games g = new Games(id,name,rating,review);
	    	games.add(g);
	    	System.out.println(g);
    	}
    	
    	rs.close();
    	return games;//return array list
    }
    //method to update the game rating of a game
    public ArrayList<Games> AddRating(String gName,int rate) throws SQLException
    {
    	ArrayList<Games> games = new ArrayList<Games>();
    	
    	Connection con = this.getConnection();
    	Statement stmt = con.createStatement() ;
    	ResultSet rs = stmt.executeQuery( "SELECT gamerating FROM GAMES where gamename = '"+gName+"'");
    	
    	rs.next();
	    int ratings = rs.getInt(1);
	    int averageRating =0;
	    
	    averageRating = (int)(ratings+rate)/2;
	    
	    String query ="Update Games Set gamerating =? where gamename = ?";
	    PreparedStatement preparedStmt = con.prepareStatement(query);
	    preparedStmt.setInt   (1, averageRating);
	    preparedStmt.setString(2, gName);
	      
	    preparedStmt.executeUpdate();	
	    //get the updated games from database
	    ResultSet newRs = stmt.executeQuery("SELECT * FROM GAMES");
	    
	    while(newRs.next())
    	{
	    	int id = newRs.getInt(1);
	    	String name = newRs.getString(2);
	    	int rating = newRs.getInt(3);
	    	String review = newRs.getString(4);
	    	
	    	Games g = new Games(id,name,rating,review);
	    	games.add(g);
	    	System.out.println(g);
    	}
	    
    	rs.close();
    	return games;//return updated games list
    }

    
}