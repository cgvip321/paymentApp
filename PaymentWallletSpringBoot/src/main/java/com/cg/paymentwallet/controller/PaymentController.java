package com.cg.paymentwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.AlreadyExistingException;
import com.cg.paymentwallet.exception.CreateAccountException;
import com.cg.paymentwallet.exception.InsufficientBalanceException;
import com.cg.paymentwallet.exception.InvalidAmountException;
import com.cg.paymentwallet.exception.InvalidCustomerIdException;
import com.cg.paymentwallet.exception.InvalidToAccountNoException;
import com.cg.paymentwallet.service.IPaymentWalletService;

@RestController
public class PaymentController 
{
	@Autowired
	IPaymentWalletService paymentService;
	
	@PostMapping("/paymentWallet")
	public String createAccount(@RequestParam String customerName,@RequestParam String phoneNo,@RequestParam double amount) throws CreateAccountException,AlreadyExistingException
	{
		int customerId=(int)(Math.random()*1000);
		if(paymentService.validateCreateDetails(customerName,phoneNo,amount))
		{
			if(paymentService.validatePhoneNoNotExists(phoneNo))
			{
				return paymentService.createAccount(customerId, customerName, phoneNo, amount);
			}
			else 
				throw new AlreadyExistingException();
		}
		else 
			throw new CreateAccountException();
	}
	
	@GetMapping("/paymentWallet/{customerId}")
	public String showBalance(int customerId) throws InvalidCustomerIdException
	{
		if(paymentService.validateCustomerId(customerId))
		{
			return "Your Account Balance Is: "+paymentService.showBalance(customerId);
		}
		else 
			throw new InvalidCustomerIdException();
	}
	
	@PostMapping("/paymentWallet/deposit")
	public String deposit(@RequestParam Integer customerId,@RequestParam double amount) throws InvalidCustomerIdException,InvalidAmountException
	{
		if(paymentService.validateCustomerId(customerId))
		{
			if(paymentService.validateAmount(amount))
			{
				return "Deposited.....Your New Account Balance Is: "+paymentService.deposit(customerId, amount);
			}
			else 
				throw new InvalidAmountException();
		}
		else 
			throw new InvalidCustomerIdException();
	}
	
	@PostMapping("/paymentWallet/withdraw")
	public String withdraw(@RequestParam Integer customerId,@RequestParam double amount) throws InvalidCustomerIdException,InsufficientBalanceException
	{
		if(paymentService.validateCustomerId(customerId))
		{
			if(paymentService.checkSufficientBalance(customerId,amount))
			{
				return "Withdrawn......Your New Account Balance Is: "+paymentService.withdraw(customerId, amount);
			}
			else 
				throw new InsufficientBalanceException();
		}
		else 
			throw new InvalidCustomerIdException();
	}
	@PostMapping("/paymentWallet/fundTransfer")
	public String fundTransfer(@RequestParam int customerId,@RequestParam String toAccountPhoneNo,@RequestParam double amount)throws InvalidCustomerIdException,InvalidToAccountNoException,InvalidAmountException,InsufficientBalanceException
	{
		if(paymentService.validateCustomerId(customerId))
		{
			if(paymentService.validateToAccountNo(toAccountPhoneNo))
			{
				if(paymentService.checkSufficientBalance(customerId, amount))
				{
					return "Fund Transfer Complete....Your New Account Balance Is: "+paymentService.fundTransfer(customerId, toAccountPhoneNo, amount);
				}
				else 
					throw new InsufficientBalanceException();
			}
			else 
				throw new InvalidToAccountNoException();
		}
		else 
			throw new InvalidCustomerIdException();
	}

	List<Transaction> printTransaction(int customerId) throws InvalidCustomerIdException
	{
		if(paymentService.validateCustomerId(customerId))
		{
			return paymentService.printTransaction(customerId);
		}
		else 
			throw new InvalidCustomerIdException();
	}
	
}
