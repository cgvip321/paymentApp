package com.cg.paymentwallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.paymentwallet.exception.AlreadyExistingException;
import com.cg.paymentwallet.exception.CreateAccountException;
import com.cg.paymentwallet.exception.InsufficientBalanceException;
import com.cg.paymentwallet.exception.InvalidAmountException;
import com.cg.paymentwallet.exception.InvalidCustomerIdException;
import com.cg.paymentwallet.exception.InvalidToAccountNoException;

@ControllerAdvice
public class PaymentControllerExceptionHandler
{
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	AlreadyExistingException alreadyExistingExceptionHandler(AlreadyExistingException e)
	{
		return new AlreadyExistingException("400","Account Already Exists...");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	CreateAccountException createAccountExceptionHandler(CreateAccountException e)
	{
		return new  CreateAccountException("400","Invalid Details...");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	InsufficientBalanceException insufficientBalanceExceptionHandler(InsufficientBalanceException e)
	{
		return new InsufficientBalanceException("400","Sorry!....Insufficient Balance...");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	InvalidAmountException invalidAmountExceptionHandler(InvalidAmountException e)
	{
		return new InvalidAmountException("400","Incorrect Amount");
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	InvalidCustomerIdException invalidCustomerIdExceptionHandler(InvalidCustomerIdException e)
	{
		return new InvalidCustomerIdException("404","Customer Id Does Not Exists");
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	InvalidToAccountNoException invalidToAccountNoExceptionHandler(InvalidToAccountNoException e)
	{
		return new InvalidToAccountNoException("404","Benificary Account No. Does Not Exists");
	}
}
