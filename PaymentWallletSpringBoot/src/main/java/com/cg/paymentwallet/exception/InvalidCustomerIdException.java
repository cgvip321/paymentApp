package com.cg.paymentwallet.exception;

public class InvalidCustomerIdException extends CustomException
{
	private String status;
	private String message;
	public InvalidCustomerIdException()
	{
		super();
	}
	public InvalidCustomerIdException(String status, String message) 
	{
		super();
	}
}
