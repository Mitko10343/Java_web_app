/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */

package com.assignment.games.dao;

public class Games {
	
	//Defining Games Variables
	private int id;
	private String name;
	private int rating;
	private String review;
	
	//Constructor
	public Games(int id, String name, int rating, String review) {
		this.setId(id);
		this.setName(name);
		this.setRating(rating);
		this.setReview(review);
	}
	
	//getter and setter methods
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	

}
