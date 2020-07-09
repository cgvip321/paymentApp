package com.cg.paymentwallet.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="payment_account")
public class Account 
{
	@Id
	@Column(length=10)
	private String accountNo;
	@OneToOne(cascade=CascadeType.ALL)
	private Wallet wallet;
	
	public Account()
	{
		super();
	}

	
	
	public Account(String accountNo) {
		super();
		this.accountNo = accountNo;
	}



	public Account(Customer customer) {
		super();
		accountNo = customer.getPhoneNo();
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Customer customer) {
		accountNo = customer.getPhoneNo();
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "AccountNo=  " + accountNo + "\n\nWallet=  " + wallet;
	}

	

	
}
