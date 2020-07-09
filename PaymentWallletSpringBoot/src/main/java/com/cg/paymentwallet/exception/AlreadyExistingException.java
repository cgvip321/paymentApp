package com.cg.paymentwallet.exception;

public class AlreadyExistingException extends CustomException 
{
	private String status;
	private String message;
	
	public AlreadyExistingException() 
	{
		super();
	}

	public AlreadyExistingException(String status, String message) 
	{
		super();
	}
}
