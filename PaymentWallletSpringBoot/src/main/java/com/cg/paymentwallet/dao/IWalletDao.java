package com.cg.paymentwallet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.exception.InvalidCustomerIdException;

@Repository
public interface IWalletDao
{
	String createAccount(int customerId,String customerName,String phoneNo,double initBalance);
	double showBalance(int customerID);
	double deposit(int custId,double amount);
	double withdraw(int customerId,double amount);
	double fundTransfer(int custId,String ToAccountNo,double amount);
	List<Transaction> printTransaction(int custId);
}
