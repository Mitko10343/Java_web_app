/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */

package com.assignment.commandExceptions;

public class CommandCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommandCreationException() {
    }

    public CommandCreationException(String aMessage) {
        super(aMessage);
    }	
	
}