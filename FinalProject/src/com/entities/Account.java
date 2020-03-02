/**
 * 
 * com.entities is a group of entities (Account, Bank, Customer, Payment).
 * 
 */
package com.entities;

import java.text.DecimalFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.Pattern;

/**
 * This is a model class to hold account information
 * @see com.entities.Bank
 * @see com.entities.Customer
 * @see com.entities.Payment
 * @author Victor Zenin
 * @version 1.4.
 */
@Entity 
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Account_ID")
	int account_ID;		
	
	@OneToOne
	@JoinColumn(name = "Customer_ID", referencedColumnName="Customer_ID", insertable = true, updatable = true)
	Customer customer;
	
	@Basic
	@Column(name = "AccountNumber")
	@Pattern(regexp="([0-9]{9})", message="Account number must have 9 numbers : \"123456789\"")
	String accountNumber;
	
	@Basic
	@Column(name = "Type")
	String type;
	
	@Basic
	@Column(name = "Active")
	boolean active;
	
	@Basic
	@Column(name = "Balance")
	double balance;
	
	@OneToOne
	@JoinColumn(name = "bank_ID", referencedColumnName="bank_ID", insertable = true, updatable = true)
	Bank bank;
	/**
    * No-argument constructor initializes instance variables
    * @see #setAccount_ID(int)
    * @see #setCustomer(Customer)
    * @see #setAccountNumber(String)
    * @see #setType(String)
    * @see #setActive(boolean)
    * @see #setBalance(double)
    * @see #setBank(Bank)
    */
	public Account() {
		this.setAccount_ID(0);
		this.setBank(new Bank());
		this.setCustomer(new Customer());
		this.setAccountNumber("");
		this.setType("Checking");
		this.setActive(true);
		this.setBalance(0.00);
	}
   /**
    * Account constructor using instance of other account 
    * @see #setAccount_ID(int)
    * @see #setCustomer(Customer)
    * @see #setAccountNumber(String)
    * @see #setType(String)
    * @see #setActive(boolean)
    * @see #setBalance(double)
    * @see #setBank(Bank)
    * @param	account	Account
    */
	public Account(Account account) {
		this.setAccount_ID(account.getAccount_ID());
		this.setBank(account.getBank());
		this.setCustomer(account.getCustomer());
		this.setAccountNumber(account.getAccountNumber());
		this.setType(account.getType());
		this.setActive(account.isActive());
		this.setBalance(account.getBalance());
	}
	
   /**
    * Account constructor is assigning all values to fields of instance of class
    * @see #setAccount_ID(int)
    * @see #setCustomer(Customer)
    * @see #setAccountNumber(String)
    * @see #setType(String)
    * @see #setActive(boolean)
    * @see #setBalance(double)
    * @see #setBank(Bank)
    * @param	account_ID	unique identificator of account
    * @param	bank	bank where the account is opened
    * @param	customer	owner of account
    * @param	accountNumber	number of account
    * @param	type	type of account (Checking, Savings, Credit)
    * @param	active	property: active or inactive account
    * @param	balance	current balance of account
    */
	public Account(int account_ID,
					Bank bank,
					Customer customer,
					String accountNumber,
					String type,
					boolean active,
					double balance) {
		
		this.setAccount_ID(account_ID);
		this.setBank(bank);
		this.setCustomer(customer);
		this.setAccountNumber(accountNumber);
		this.setType(type);
		this.setActive(active);
		this.setBalance(balance);
	}

   /**
    * Gets the account ID
    * @return	an unique identificator of account
    * specifying the account ID
    */
	public int getAccount_ID() {
		return account_ID;
	}
	
   /**
    * Sets the account ID
    * @param	account_ID	unique identificator of account
    */
	public void setAccount_ID(int account_ID) {
		this.account_ID = account_ID;
	}
	
   /**
    * Gets the account number
    * @return	number of account
    */
	public String getAccountNumber() {
		return accountNumber;
	}
    /**
     * Sets the account number
     * @param	accountNumber	account number
     */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
   /**
    * Gets the type
    * @return	type of account (Checking, Savings, Credit)
    */
	public String getType() {
		return type;
	}
    /**
     * Sets the type
     * @param	type	type of account (Checking, Savings, Credit)
     */
	public void setType(String type) {
		this.type = type;
	}
   /**
    * Gets the active
    * @return property: active or inactive account 
    */
	public boolean isActive() {
		return active;
	}	
    /**
     * Sets the active
     * @param	active	property: active or inactive account
     */
	public void setActive(boolean active) {
		this.active = active;
	}
   /**
    * Gets the balance 
    * @return current balance of account 
    */
	public double getBalance() {
		return balance;
	}
   /**
    * Gets the balance for JSP page
    * <p>
    * Use DecimalFormat"###,###,##0.00"
    * @return current balance of account 
    * @see DecimalFormat
    */
	public String getBalanceOutput() {
		DecimalFormat formatFullBalance = new DecimalFormat("###,###,##0.00");
	    String outputFullBalance = formatFullBalance.format(this.getBalance());		
		return outputFullBalance;
	}
    /**
     * Sets the balance
     * @param	balance	current balance of account
     */
	public void setBalance(double balance) {
		this.balance = balance;
	}
   /**
    * Gets the bank
    * @return bank where the account is opened 
    * @see Bank
    */
	public Bank getBank() {
		return bank; 
	}
    /**
     * Sets the bank
     * @param	bank	bank where the account is opened
     */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
    /**
     * Sets the bank
     * @param	bank_ID	bank_ID of bank where the account is opened
     * @param	routingNumber	routing number of bank where the account is opened
     * @param	name	name of bank where the account is opened
     * @param	address	address of bank where the account is opened
     * @param	region	region of bank where the account is opened
     */
	public void setBank(int bank_ID, 
			String routingNumber, 
			String name,
			String address,
			String region) {
		this.bank.setBank_ID(bank_ID);
		this.bank.setRoutingNumber(routingNumber);
		this.bank.setName(name);
		this.bank.setAddress(address);	
		this.bank.setRegion(region);
	}
   /**
    * Gets the customer
    * @return customer, owner of account  
    * @see Customer
    */
	public Customer getCustomer() {
		return customer; 
	}
    /**
     * Sets the customer
     * @param	customer	customer, owner of account
     */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * @return	"textually represents" of account
     */
	@Override
	public String toString() {
		return "Account [" + 
		this.getAccount_ID() + ", " +
		this.getCustomer() + ", " +
		this.getAccountNumber() + ", " + 
		this.getType() + ", " + 
		this.isActive() + ", " + 
		this.getBalance() + ", " + 
		this.getBank() + "]";
	}
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * For JSP page - "Payments list" page
     * @return	"textually represents" of account
     */
	public String toStringForViewPaymentsPage() {
		return	this.getAccountNumber() + ", " + 
				this.bank.getName() + " (" + this.bank.getRegion() + ")" ;
	}	
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * For JSP page - "Create a transfer to another my account"
     * @return	"textually represents" of account
     */
	public String toStringForTransactionTo() {
		return	this.getAccountNumber() + ", " + 
				this.bank.getName() + " (" + this.bank.getRegion() + ")" + " - $" + this.getBalance();
	}	
    /**
     * Indicates whether other object with data type of Account is "equal to" this one.
     * 
     * @param	obj	other account
     * @return	true (if "equal to") or false
     */
	@Override
	public boolean equals(Object obj) {	
		Account account = (Account) obj;
		if (!this.getAccountNumber().equals(account.getAccountNumber()))
			return false;
		if (!Boolean.valueOf(this.isActive()).equals(Boolean.valueOf(account.isActive())))
			return false;				
		if (!this.getBank().equals(account.getBank()))
			return false;	
		if (!this.getCustomer().equals(account.getCustomer()))
			return false;	
		if (!this.getType().equals(account.getType()))
			return false;	
		return true;
	}
}
