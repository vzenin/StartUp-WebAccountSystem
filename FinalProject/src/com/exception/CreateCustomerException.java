package com.exception;

/**
 * This is a CreateCustomerException class.
 * <p>
 * This exception is happening if the system cannot create the new user. An user exists already with the same login
 * @see com.service.CustomerDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@SuppressWarnings("serial")
public class CreateCustomerException extends Exception {
   /**
    * CreateCustomerException constructor
    * @param	errorMessage	String
    */
	public CreateCustomerException(String errorMessage) {
		super(errorMessage);
	}
}
