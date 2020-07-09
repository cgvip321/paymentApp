package com.cg.paymentwallet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.AlreadyExistingException;
import com.cg.paymentwallet.exception.CreateAccountException;
import com.cg.paymentwallet.exception.InsufficientBalanceException;
import com.cg.paymentwallet.exception.InvalidCustomerIdException;
import com.cg.paymentwallet.exception.InvalidToAccountNoException;

@Service
public interface IPaymentWalletService 
{
	String createAccount(int customerId,String customerName,String phoneNo,double initBalance);
	double showBalance(int customerID);
	double deposit(int custId, double amount);
	double withdraw(int custId,double amount);
	double fundTransfer(int custId,String ToAccountPhoneNo,double amount);
	List<Transaction> printTransaction(int custId);
	boolean validateCreateDetails(String customerName,String phoneNo,double initBalance);
	boolean validatePhoneNoNotExists(String phoneNo);
	boolean validateCustomerId(int customerId);
	boolean validateAmount(double amount);
	boolean checkSufficientBalance(int customerId,double amount);
	boolean validateToAccountNo(String toAccountPhoneNo);
}
