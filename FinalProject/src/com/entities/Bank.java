package com.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * This is a model class to hold bank information
 *
 * @see com.entities.Account
 * @see com.entities.Customer
 * @see com.entities.Payment
 * @author Victor Zenin
 * 
 * @version 1.1.
 * 
 */

@Entity 
@Table(name = "bank")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Bank_ID")
	int bank_ID;
	
	@Basic
	@Column(name="RoutingNumber")
	String routingNumber;
	
	@Basic
	@Column(name="Name")
	String name;
	
	@Basic
	@Column(name="Address")
	String address;

	@Basic
	@Column(name="Region")
	String region;	
	
	/**
    * No-argument constructor initializes instance variables
    * @see #setBank_ID(int)
    * @see #setRoutingNumber(String)
    * @see #setName(String)
    * @see #setAddress(String)
    * @see #setRegion(String)
    */
	public Bank() {
		this.setBank_ID(0);
		this.setRoutingNumber("001000001");
		this.setName("Name of Bank");
		this.setAddress("the USA");
		this.setRegion("New York");
	}
	
   /**
    * Bank constructor
    * 
    * @see #setBank_ID(int)
    * @see #setRoutingNumber(String)
    * @see #setName(String)
    * @see #setAddress(String)
    * @see #setRegion(String)
    * 
    * @param	bank_ID	unique identificator of bank
    * @param	routingNumber	routing number of bank
    * @param	name	name of bank
    * @param	address	address of bank
    * @param	region	region of bank
    * 
    */
	public Bank(int bank_ID, 
				String routingNumber, 
				String name,
				String address,
				String region) {
		
		this.setBank_ID(bank_ID);
		this.setRoutingNumber(routingNumber);
		this.setName(name);
		this.setAddress(address);	
		this.setRegion(region);
	}
	
   /**
    * Gets the bank ID
    * @return	an unique identificator of bank
    * specifying the bank ID
    */
	public int getBank_ID() {
		return bank_ID;
	}
	
   /**
    * Sets the bank ID
    * @param	bank_ID	unique identificator of bank
    */
	public void setBank_ID(int bank_ID) {
		this.bank_ID = bank_ID;
	}
	
   /**
    * Gets the routing number
    * @return	routing number of bank
    */
	public String getRoutingNumber() {
		return routingNumber;
	}
	
    /**
     * Sets the routing number
     * @param	routingNumber	routing number of bank
     */
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	
   /**
    * Gets the name
    * @return	name of bank
    */
	public String getName() {
		return name;
	}
	
    /**
     * Sets the name
     * @param	name	name of bank
     */
	public void setName(String name) {
		this.name = name;
	}
	
   /**
    * Gets the address
    * @return	address of bank
    */
	public String getAddress() {
		return address;
	}
	
    /**
     * Sets the address
     * @param	address	address of bank
     */
	public void setAddress(String address) {
		this.address = address;
	}
	
   /**
    * Gets the region
    * @return	region of bank
    */
	public String getRegion() {
		return region;
	}

    /**
     * Sets the region
     * @param	region	region of bank
     */
	public void setRegion(String region) {
		this.region = region;
	}
	
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * @return	"textually represents" of bank
     */
	@Override
	public String toString() {
		return "Bank [" + 
		this.getBank_ID() + ", " +
		this.getRoutingNumber() + ", " +
		this.getName() + ", " +
		this.getAddress() + ", " +
		this.getRegion() + "]";
	}
	
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * For JSP page - "Edit Account" page
     * @return	"textually represents" of bank
     */
	public String toStringForEditAccountPage() {
		return	this.getName() + " (" +
				this.getRegion() + ") " +
				this.getRoutingNumber();
	}
	
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * For JSP page - "View Accounts" page
     * @return	"textually represents" of bank
     */
	public String toStringForViewAccountsPage() {
		return this.getName() + " (" + this.getRegion() + ")" ; 
	}
	
    /**
     * Indicates whether other object with data type of Bank is "equal to" this one.
     * 
     * @param	obj	other bank
     * @return	true (if "equal to") or false
     */
	@Override
	public boolean equals(Object obj) {
		
		Bank bank = (Bank) obj;
		
		if (!this.getName().equals(bank.getName()))
			return false;
		if (!this.getRegion().equals(bank.getRegion()))
			return false;		
		if (!this.getRoutingNumber().equals(bank.getRoutingNumber()))
			return false;		
		if (!this.getAddress().equals(bank.getAddress()))
			return false;	
		
		return true;
	}
	
}

