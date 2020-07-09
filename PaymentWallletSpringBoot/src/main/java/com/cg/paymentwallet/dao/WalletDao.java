package com.cg.paymentwallet.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.paymentwallet.bean.Account;
import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.bean.Wallet;
import com.cg.paymentwallet.exception.InvalidCustomerIdException;

@Repository
@Transactional
public class WalletDao implements IWalletDao
{
	@PersistenceContext
	EntityManager em;

	LocalDate localDate = LocalDate.now();
	@Override
	public String createAccount(int customerId,String customerName, String phoneNo, double initBalance)
	{	
		Wallet wallet = new Wallet(phoneNo,initBalance);
		Account account = new  Account(phoneNo);
		account.setWallet(wallet);
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Customer customer= new Customer(customerId,customerName,phoneNo,date);
		customer.setAccount(account);
		em.persist(customer);
		return "Account Created...Your Unique CustomerId Is = "+customerId;
	}

	@Override
	public double showBalance(int customerID)
	{
		Customer customer= em.find(Customer.class, customerID);
		
		return customer.getAccount().getWallet().getBalance();
	}

	@Override
	public double deposit(int custId,double amount) 
	{
		Customer dep_customer = em.find(Customer.class,custId); 
		double newBalance =  dep_customer.getAccount().getWallet().getBalance()+amount;
		dep_customer.getAccount().getWallet().setBalance(newBalance);
		Transaction transaction = new Transaction();
		//transaction.setTransactionNo();
		transaction.setTransactionAmount(amount);
		transaction.setService("Deposit");
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		transaction.setTranscationDate(date);
		transaction.setTransferringAccount("Self");
		transaction.setWallet(dep_customer.getAccount().getWallet());
		dep_customer.getAccount().getWallet().setTransaction(transaction);
		em.persist(transaction);
		return newBalance;
	}

	@Override
	public double withdraw(int custId, double amount) 
	{
		Customer dep_customer = em.find(Customer.class,custId); 
		double newBalance =  dep_customer.getAccount().getWallet().getBalance()-amount;
		dep_customer.getAccount().getWallet().setBalance(newBalance);
		Transaction transaction = new Transaction();
		//transaction.setTransactionNo();
		transaction.setTransactionAmount(amount);
		transaction.setService("Withdraw");
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		transaction.setTranscationDate(date);
		transaction.setTransferringAccount("Self");
		transaction.setWallet(dep_customer.getAccount().getWallet());
		dep_customer.getAccount().getWallet().setTransaction(transaction);
		em.persist(transaction);
		return newBalance;
	}

	@Override
	public double fundTransfer(int custId,String toAccountNo, double amount) 
	{
		Customer from = em.find(Customer.class,custId);
		Account to = em.find(Account.class,toAccountNo);
		double from_currentBalance=from.getAccount().getWallet().getBalance();
		double from_newBalance = from_currentBalance-amount;
		double to_currentBalance=to.getWallet().getBalance();
		double to_newBalance = to_currentBalance+amount;
		
		from.getAccount().getWallet().setBalance(from_newBalance);
		to.getWallet().setBalance(to_newBalance);
		
		Transaction transaction1 = new Transaction();
		//transaction.setTransactionNo();
		transaction1.setTransactionAmount(amount);
		transaction1.setService("Sent");
		Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		transaction1.setTranscationDate(date1);
		transaction1.setTransferringAccount(to.getAccountNo());
		transaction1.setWallet(from.getAccount().getWallet());
		from.getAccount().getWallet().setTransaction(transaction1);
		
		Transaction transaction2 = new Transaction();
		//transaction.setTransactionNo();
		transaction2.setTransactionAmount(amount);
		transaction2.setService("Recieved");
		Date date2 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		transaction2.setTranscationDate(date2);
		transaction2.setTransferringAccount(from.getAccount().getAccountNo());
		transaction2.setWallet(to.getWallet());
		to.getWallet().setTransaction(transaction2);
		
		em.persist(transaction1);
		em.persist(transaction2);
		return from_newBalance;
	}

	@Override
	public List<Transaction> printTransaction(int custId)
	{
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c where customerId=:cust", Customer.class);
		query.setParameter("cust",custId);
		Customer cust= query.getSingleResult();
		List<Transaction> transactions= cust.getAccount().getWallet().getTransactions();
		return transactions;
		
	}
	
	
	
}
