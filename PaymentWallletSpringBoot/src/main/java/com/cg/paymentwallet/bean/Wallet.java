package com.cg.paymentwallet.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="payment_wallet")
public class Wallet 
{	
	@Id
	@Column(length=10)
	private String id;
	@Column(length=10)
	private double balance;
	
	@JsonManagedReference
	@OneToMany(mappedBy="wallet",cascade=CascadeType.ALL)
	private List<Transaction> transactions=new ArrayList<>();
	

	public Wallet() 
	{
		super();
		
	}
	
	public Wallet(String id, double balance)
	{
		super();
		this.id = id;
		this.balance = balance;
	}

	public String getId()
	{
		return id;
	}
	
	public void setId(Account account)
	{
		this.id=account.getAccountNo();
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void setBalance(double balance)
	{
		this.balance=balance;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransaction(Transaction transaction) 
	{
		this.transactions.add(transaction);
	}
	
	@Override
	public String toString() {
		return "Id=  " + id + "\n\nBalance=  " + balance;
	}
}
