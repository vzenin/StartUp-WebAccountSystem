package com.exception;

/**
 * This is a SpringException class.
 * <p>
 * This exception is happening if the system has RuntimeException.
 * <p>
 * there will be transition to a page "error.jsp" (the page: "Error")
 * @see com.service.CustomerDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@SuppressWarnings("serial")
public class SpringException extends RuntimeException{
	private String exceptionMsg;

	public SpringException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg(){
		return this.exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}