package com.cg.paymentwallet.exception;

public class InsufficientBalanceException extends CustomException 
{
	private String status;
	private String message;
	public InsufficientBalanceException()
	{
		super();
	}
	public InsufficientBalanceException(String status, String message)
	{
		super();
	}
	
}
