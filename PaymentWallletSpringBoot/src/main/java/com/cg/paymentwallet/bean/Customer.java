package com.cg.paymentwallet.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="payment_customer")
public class Customer 
{
	@Id
	@Column(length=10)
	private int customerId;
	@Column(length=40)
	@JsonProperty(value="customerName")
	private String customerName;
	@Column(length=10)
	@JsonProperty(value="phoneNo")
	private String phoneNo;
	@Column(name="CreatedDate",length=25)
	@Temporal(TemporalType.DATE)
	@JsonProperty(value="createdDate")
	private Date createdDate;

	@OneToOne(cascade=CascadeType.ALL)
	private Account account;
	
	public Customer() 
	{
		super();
	}
	
	
	public Customer(int customerId, String customerName, String phoneNo, Date createdDate)
	{
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.createdDate = createdDate;
	}

	public int getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Account getAccount() 
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	@Override
	public String toString() 
	{
		return "CustomerId=  " + customerId + "\n\nPhoneNo=  " + phoneNo + "\\n\nCustomerName=  " + customerName+ "\\n\nAccount=  " + account + "]";
	}



	
	
	
}
