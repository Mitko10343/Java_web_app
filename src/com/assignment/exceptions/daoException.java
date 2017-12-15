/*Third year Software engineering assignment
 * Game review web application
 * Written By: Dimiter Dinkov
 * Student Number: C15334276
 * */

package com.assignment.exceptions;

import java.sql.SQLException;


public class daoException extends SQLException {

	private static final long serialVersionUID = 1L;

	public daoException() {
    }

    public daoException(String aMessage) {
        super(aMessage);
    }
    
}