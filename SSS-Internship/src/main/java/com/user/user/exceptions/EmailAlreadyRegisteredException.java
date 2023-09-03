package com.user.user.exceptions;


public class EmailAlreadyRegisteredException extends RuntimeException {

//	private static final long serialVersionUID = 1L;
	private String message;
	 
	    public EmailAlreadyRegisteredException() {}
	 
	    public EmailAlreadyRegisteredException(String msg)
	    {
	        super(msg);
	        this.message = msg;
	    }

}
