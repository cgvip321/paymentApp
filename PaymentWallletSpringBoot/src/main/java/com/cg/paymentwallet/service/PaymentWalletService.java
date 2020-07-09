package com.cg.paymentwallet.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.cg.paymentwallet.bean.Account;
import com.cg.paymentwallet.bean.Customer;
import com.cg.paymentwallet.bean.Transaction;
import com.cg.paymentwallet.dao.IWalletDao;
import com.cg.paymentwallet.dao.WalletDao;
import com.cg.paymentwallet.exception.AlreadyExistingException;
import com.cg.paymentwallet.exception.CreateAccountException;
import com.cg.paymentwallet.exception.InsufficientBalanceException;
import com.cg.paymentwallet.exception.InvalidCustomerIdException;
import com.cg.paymentwallet.exception.InvalidToAccountNoException;

@Service
public class PaymentWalletService implements IPaymentWalletService
{
	@PersistenceContext
	EntityManager em;
	
	IWalletDao walletConnection = new WalletDao();

	@Override
	public String createAccount(int customerId,String customerName, String phoneNo, double initBalance)
	{
		return walletConnection.createAccount(customerId,customerName, phoneNo, initBalance);
		
	}
	
	@Override
	public double showBalance(int customerID)
	{
		return walletConnection.showBalance(customerID);
	}

	@Override
	public double deposit(int custId,double amount)
	{
		return walletConnection.deposit(custId,amount);
		
	}

	@Override
	public double withdraw(int custId, double amount) 
	{
		
		return walletConnection.withdraw(custId, amount);
	}

	@Override
	public double fundTransfer(int custId,String ToAccountNo, double amount) 
	{
		return walletConnection.fundTransfer(custId, ToAccountNo, amount);
	}

	@Override
	public List<Transaction> printTransaction(int custId)
	{
		
		return walletConnection.printTransaction(custId);
	}

	@Override
	public boolean validateCreateDetails(String customerName,String phoneNo,double initBalance) 
	{
		if(customerName!=null && phoneNo!=null && (!(initBalance<0)))
		{
			return true;
		}
		return false;
		
	}

	@Override
	public boolean validatePhoneNoNotExists(String phoneNo) 
	{
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c",Customer.class);
		List<Customer> customerList= query.getResultList();
		for(Customer myCustomer:customerList)
		{
			if(phoneNo.equals(myCustomer.getPhoneNo()))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean validateCustomerId(int customerId) 
	{
		Customer customer= em.find(Customer.class, customerId);
		if(customer==null)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean validateAmount(double amount)
	{
		if(amount>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean checkSufficientBalance(int customerId,double amount) 
	{
		Customer dep_customer = em.find(Customer.class,customerId); 
		if(dep_customer.getAccount().getWallet().getBalance()<amount)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean validateToAccountNo(String toAccountPhoneNo) 
	{
		Account to = em.find(Account.class,toAccountPhoneNo);
		if(to==null)
		{
			return false;
		}
		return true;
	}

}
