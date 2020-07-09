package com.cg.paymentwallet.exception;

public class CustomException extends Exception
{
	private String status;
	private String message;
	public CustomException() 
	{
		super();
	}

	public CustomException(String status, String message) 
	{
		super();
		this.status = status;
		this.message = message;
	}
}
