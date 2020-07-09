package com.cg.paymentwallet.exception;

public class CreateAccountException extends CustomException 
{
	private String status;
	private String message;
	public CreateAccountException() 
	{
		super();
	}
	public CreateAccountException(String status, String message) 
	{
		super();
	}
	
}
