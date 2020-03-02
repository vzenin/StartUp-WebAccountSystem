package com.entities;


import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * This is a model class to hold payment information
 * 
 * @see com.entities.Bank
 * @see com.entities.Customer
 * @see com.entities.Account
 * @author Victor Zenin
 * 
 * @version 1.0.
 * 
 */

@Entity 
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Payment_ID")
	private int payment_ID;
	
	@Basic
	@Column(name="Date")
	private String date;
	
	@Basic
	@Column(name="Recipient")
	private String recipient;
	
	@Basic
	@Column(name="Amount")
	private double amount;
	
	@Basic
	@Column(name="Method")
	private int method;
	
	@Basic
	@Column(name="Description")
	private String description;	
	
	@OneToOne
	@JoinColumn(name = "account_ID", referencedColumnName="account_ID", insertable = true, updatable = true)
	private Account account;
	
	/**
    * No-argument constructor initializes instance variables
    */
	public Payment() {
		payment_ID = 0;
		account = new Account();
		
		//default value of date is a current date
		LocalDate now = LocalDate.now();  
		date =  now.toString();
		
		recipient = "Optimum";
		amount = 0.0;
		//possible values: 0 - credit, 1 - debit
		//default value is 0
		method = 0;
		description = "Monthly payment";	
	}

   /**
    * Payment constructor using instance of account 
    * 
    * @param	account	account from where spend money
    */
	public Payment(Account account) {
		this();
		this.account = new Account(account);
	}
	
   /**
    * Payment constructor
    * 
    * @see #setPayment_ID(int)
    * @see #setAccount(Account)
    * @see #setDate(String)
    * @see #setRecipient(String)
    * @see #setAmount(double)
    * @see #setMethod(int)
    * @see #setDescription(String)
    * 
    * @param	payment_ID	unique identificator of payment
    * @param	account	account from where spend money
    * @param	date	date of payment
    * @param	recipient	recipient of payment
    * @param	amount	amount of payment
    * @param	method	method of payment (0 - credit, 1 - debit)
    * @param	description	description of payment
    */
	public Payment(int payment_ID, 
			Account account, 
			String date, 
			String recipient,
			double amount,
			int method,
			String description	) {
		this.setPayment_ID(payment_ID);
		this.setAccount(account);
		this.setDate(date);
		this.setRecipient(recipient);
		this.setAmount(amount);
		this.setMethod(method);
		this.setDescription(description);	
	}
	
   /**
    * Gets the payment ID
    * @return	an unique identificator of payment
    * specifying the payment ID
    */
	public int getPayment_ID() {
		return payment_ID;
	}

   /**
    * Sets the payment ID
    * @param	payment_ID	unique identificator of payment
    */
	public void setPayment_ID(int payment_ID) {
		this.payment_ID = payment_ID;
	}

   /**
    * Gets the account
    * @return	account from where spend money
    * @see Account
    */
	public Account getAccount() {
		return account;
	}
	
    /**
     * Gets stage of account_ID (less or equal 0)
     * <p>
     * The method was created for JSP Page: "createtransactionbetween.jsp"
     * @return	true (if account_ID less or equal 0) or false
     */
	public boolean getAccountToBoo() {
		return (this.getAccount().getAccount_ID() <= 0);
	}
	
   /**
    * Sets the account
    * @param	account	account from where spend money
    * @see Account
    */
	public void setAccount(Account account) {
		this.account = account;
	}

   /**
    * Gets the date
    * @return	date of payment
    */
	public String getDate() {
		return date;
	}

   /**
    * Sets the account
    * @param	date	date of payment
    */
	public void setDate(String date) {
		this.date = date;
	}

   /**
    * Gets the recipient
    * @return	recipient of payment
    */
	public String getRecipient() {
		return recipient;
	}

    /**
     * Gets stage of recipient (less 0)
     * <p>
     * The method was created for JSP Page: "createtransactionto.jsp"
     * @return	true (if recipient less 0) or false
     */
	public boolean getRecipientToBoo() {
		return ((Integer.parseInt(recipient) < 0) ? true : false);
	}
	
   /**
    * Sets the recipient
    * @param	recipient	recipient of payment
    */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

   /**
    * Gets the amount
    * @return	amount of payment
    */
	public double getAmount() {
		return amount;
	}
	
	/**
    * Gets the amount for JSP page
    * <p>
    * Use DecimalFormat"###,###,##0.00"
    * @return  amount of payment
    * @see DecimalFormat
    */
	public String getAmountOutput() {
		DecimalFormat formatFullAmount = new DecimalFormat("$###,###,##0.00");
	    String outputFullAmount = formatFullAmount.format(this.getAmount());		
		return outputFullAmount;
	}
	
   /**
    * Sets the amount
    * @param	amount	amount of payment
    */
	public void setAmount(double amount) {
		this.amount = amount;
	}

   /**
    * Gets the method
    * @return	method of payment (0 - credit, 1 - debit)
    */
	public int getMethod() {
		return method;
	}

   /**
    * Sets the method
    * @param	method	method of payment (0 - credit, 1 - debit)
    */
	public void setMethod(int method) {
		this.method = method;
	}

   /**
    * Gets the description
    * @return	description of payment
    */
	public String getDescription() {
		return description;
	}

   /**
    * Sets the description
    * @param	description	description of payment
    */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * @return	"textually represents" of payment
     */
	@Override
	public String toString() {
		return "Payment [" + 
		this.getPayment_ID() + ", " +
		this.getAccount() + ", " +
		this.getDate() + ", " + 
		this.getRecipient() + ", " + 
		this.getAmount() + ", " + 
		this.getMethod() + ", " + 
		this.getDescription() + "]";
	}
	
    /**
     * Indicates whether other object with data type of Payment is "equal to" this one.
     * 
     * @param	obj	other payment
     * @return	true (if "equal to") or false
     */
	@Override
	public boolean equals(Object obj) {
		
		Payment payment = (Payment) obj;

		if (!this.getDate().equals(payment.getDate()))
			return false;
		if (!this.getRecipient().equals(payment.getRecipient()))
			return false;
		if (!Double.valueOf(this.getAmount()).equals(Double.valueOf(payment.getAmount())))
			return false;
		if (!Integer.valueOf(this.getMethod()).equals(Integer.valueOf(payment.getMethod())))
			return false;		
		if (!this.getDescription().equals(payment.getDescription()))
			return false;		
		if (!this.getAccount().equals(payment.getAccount()))
			return false;	
		
		return true;
	}
	
}