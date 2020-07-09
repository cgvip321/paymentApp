package com.cg.paymentwallet.exception;

public class InvalidToAccountNoException extends CustomException
{
	private String status;
	private String message;
	public InvalidToAccountNoException() 
	{
		super();
	}
	public InvalidToAccountNoException(String status, String message)
	{
		super();
	}
}
