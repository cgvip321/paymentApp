package com.cg.paymentwallet.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="payment_transaction")
public class Transaction 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TransacctionNo")
	private int transactionNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="TransactionDate",length=25)
	private Date transcationDate;
	
	@Column(name="Transactiontype",length=30)
	private String service;
	
	@Column(name="ByAccount",length=10)
	private String transferringAccount;
	
	@Column(name="Amount",length=10)
	private double transactionAmount;
	
	@JsonBackReference
	@ManyToOne
	private Wallet wallet;
	
	public Transaction()
	{
		super();
	}

	public Transaction(int transactionNo, Date transcationDate, String service, String transferringAccount,double transactionAmount) 
	{
		super();
		this.transactionNo = transactionNo;
		this.transcationDate = transcationDate;
		this.service = service;
		this.transferringAccount = transferringAccount;
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionNo() {
		return transactionNo;
	}
	public String getTransferringAccount() {
		return transferringAccount;
	}

	public void setTransferringAccount(String transferringAccount) {
		this.transferringAccount = transferringAccount;
	}

	public Date getTranscationDate() {
		return transcationDate;
	}

	public void setTranscationDate(Date transcationDate) {
		this.transcationDate = transcationDate;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "TransactionNo=  " + transactionNo + "\n\nTranscationDate=  " + transcationDate + "\n\nService="+ service + "\n\nTransactionAmount=  " + transactionAmount + "\n\nWallet=  " + wallet;
	}

	 
}
