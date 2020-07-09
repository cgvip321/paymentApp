package com.cg.paymentwallet.exception;

public class InvalidAmountException extends CustomException 
{
	private String status;
	private String message;
	public InvalidAmountException()
	{
		super();
	}
	public InvalidAmountException(String status, String message) 
	{
		super();
	}
	
}
