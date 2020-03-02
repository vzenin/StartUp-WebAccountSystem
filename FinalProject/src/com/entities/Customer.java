package com.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * This is a model class to hold customer information
 * 
 * @see com.entities.Bank
 * @see com.entities.Payment
 * @see com.entities.Account
 * @author Victor Zenin
 * 
 * @version 1.0.
 * 
 */

@Entity 
@Table
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_ID")	
	private int customer_ID;
	
	@Basic
	@Column(name="firstName")
	@Size(min = 1, max = 50, message = "First name must be between {2} and {1}")
	private String firstName;
	
	@Basic
	@Column
	@Size(min = 1, max = 50, message = "Last name must be between {2} and {1}")
	private String lastName;
	
	@Basic
	@Column
	@Size(min = 0, max = 150, message = "Address must be less than or equal to {1}")
	private String address;
	
	@Basic
	@Column
	@Pattern(regexp="([0-9]{3}-{1}[0-9]{3}-{1}[0-9]{4})", message="Error phone format (000-000-0000)")
	private String phone;	
	
	@Basic
	@Column
	@Pattern(regexp="^([a-zA-Z0-9._-]+)@{1}([a-zA-Z0-9._-]+)\\.{1}([a-zA-Z]{2,6})$", message="Error email format")
	private String email;
	
	@Basic
	@Column
	private String gender;
	
	@Basic
	@Column
	@Pattern(regexp="([0-9]{4}-{1}[0-9]{2}-{1}[0-9]{2})", message="Error date format (MM/DD/YYYY)")
	private String birthday;
	
	@Basic
	@Column
	private boolean active;
	
	@Basic
	@Column(name="login")
	@Size(min = 6, max = 20, message = "Login must be between {2} and {1}")
	private String login;
	
	@Basic
	@Column
	@Size(min = 4, max = 16, message = "Password must be between {2} and {1}")
	private String password;
	
	@Basic
	@Column
	private boolean admin;	
	  
	/**
    * No-argument constructor initializes instance variables
    * @see #setCustomer_ID(int)
    * @see #setFirstName(String)
    * @see #setLastName(String)
    * @see #setAddress(String)
    * @see #setPhone(String)
    * @see #setEmail(String)
    * @see #setGender(String)
    * @see #setBirthday(String)
    * @see #setActive(boolean)
    * @see #setLogin(String)
    * @see #setPassword(String)
    * @see #setAdmin(boolean)
    * 
    */
	public Customer() {
		this.setCustomer_ID(0);
		this.setFirstName("");
		this.setLastName("");
		this.setAddress("");
		this.setPhone("");
		this.setEmail("");
		this.setGender("male");
		this.setBirthday("");
		this.setActive(true);
		this.setLogin("");
		this.setPassword("");
		this.setAdmin(false);
	}
	
   /**
    * Customer constructor
    * 
    * @see #setCustomer_ID(int)
    * @see #setFirstName(String)
    * @see #setLastName(String)
    * @see #setAddress(String)
    * @see #setPhone(String)
    * @see #setEmail(String)
    * @see #setGender(String)
    * @see #setBirthday(String)
    * @see #setActive(boolean)
    * @see #setLogin(String)
    * @see #setPassword(String)
    * @see #setAdmin(boolean)
    * 
    * @param	customer_ID	unique identificator of customer
    * @param	firstName	firstName of customer
    * @param	lastName	lastName of customer
    * @param	address	address of customer
    * @param	phone	phone of customer
    * 
    * @param	email	email of customer
    * @param	gender	firstName of customer
    * @param	dob	date of birth of customer
    * @param	active	active status of customer (1 - active, 0 - inactive)
    * @param	login	login of customer
    * @param	password	password of customer
    * @param	admin	status of admin of customer (1 - customer has a role "admin", 0 - customer doesn't have a role "admin")
    */
	public Customer(int customer_ID, 
					String firstName, 
					String lastName, 
					String address, 
					String phone, 
					String email, 
					String gender, 
					String dob, 
					boolean active, 
					String login, 
					String password, 
					boolean admin) {
	
		this.setCustomer_ID(customer_ID);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
		this.setGender(gender);
		this.setBirthday(dob);
		this.setActive(active);
		this.setLogin(login);
		this.setPassword(password);
		this.setActive(admin);
	}
	  
   /**
    * Gets the customer ID
    * @return	an unique identificator of customer
    * specifying the customer ID
    */
	public int getCustomer_ID() {
		return customer_ID;
	}
	
   /**
    * Sets the customer ID
    * @param	customer_ID	unique identificator of customer
    */
	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}
	
   /**
    * Gets the first name
    * @return	first name of customer
    */
	public String getFirstName() {
		return firstName;
	}
	
    /**
     * Sets the first name
     * @param	firstName	first name of customer
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
   /**
    * Gets the last name
    * @return	last name of customer
    */
	public String getLastName() {
		return lastName;
	}
	
    /**
     * Sets the last name
     * @param	lastName	last name of customer
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
   /**
    * Gets the address
    * @return	address of customer
    */
	public String getAddress() {
		return address;
	}
	
    /**
     * Sets the address
     * @param	address	address of customer
     */
	public void setAddress(String address) {
		this.address = address;
	}
	
   /**
    * Gets the phone
    * @return	phone of customer
    */
	public String getPhone() {
		return phone;
	}
	
    /**
     * Sets the phone
     * @param	phone	phone of customer
     */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
   /**
    * Gets the email
    * @return	email of customer
    */
	public String getEmail() {
		return email;
	}
	
    /**
     * Sets the email
     * @param	email	email of customer
     */
	public void setEmail(String email) {
		this.email = email;
	}
	
   /**
    * Gets the gender
    * @return	gender of customer
    */
	public String getGender() {
		return gender;
	}
	
    /**
     * Sets the gender
     * @param	gender	gender of customer
     */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
   /**
    * Gets the birthday
    * @return	birthday of customer
    */
	public String getBirthday() {
		return birthday;
	}
	
    /**
     * Sets the birthday
     * @param	birthday	birthday of customer
     */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
   /**
    * Gets the active
    * @return	active	status of customer (1 - active, 0 - inactive)
    */
	public boolean isActive() {
		return active;
	}
	
    /**
     * Sets the active
     * @param	active	status of customer (1 - active, 0 - inactive)
     */
	public void setActive(boolean active) {
		this.active = active;
	}
	
   /**
    * Gets the login
    * @return	login of customer
    */
	public String getLogin() {
		return login;
	}
	
    /**
     * Sets the login
     * @param	login	login of customer
     */
	public void setLogin(String login) {
		this.login = login;
	}
	
   /**
    * Gets the password
    * @return	password of customer
    */
	public String getPassword() {
		return password;
	}
	
    /**
     * Sets the password
     * @param	password	password of customer
     */
	public void setPassword(String password) {
		this.password = password;
	}  
	
   /**
    * Gets the admin
    * @return	status of admin of customer (1 - customer has a role "admin", 0 - customer doesn't have a role "admin")
    */
	public boolean isAdmin() {
		return admin;
	}
	
    /**
     * Sets the admin
     * @param	admin	status of admin of customer (1 - customer has a role "admin", 0 - customer doesn't have a role "admin")
     */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * @return	"textually represents" of customer
     */
	@Override
	public String toString() {
		return "Customer [" + this.getCustomer_ID() + ", " +
			this.getFirstName() + ", " +
			this.getLastName() + ", " +
			this.getAddress() + ", " +
			this.getPhone() + ", " +
			this.getEmail() + ", " +
			this.getGender() + ", " +
			this.getBirthday() + ", " +
			this.isActive() + ", " +
			this.getLogin() + ", " +
			this.getPassword() + ", " +
			this.isAdmin() + "]";
	}
	
    /**
     * Returns a string representation of the object. 
     * <p>
     * In general, the toString method returns a string that "textually represents" this object.
     * <p>
     * For JSP page - "View Accounts" page
     * @return	"textually represents" of customer
     */
	public String toStringForViewAccounts() {
		return	this.getFirstName() + " " +
				this.getLastName() + " (" +
				this.getLogin() + ")";
	}
	
    /**
     * Indicates whether other object with data type of Customer is "equal to" this one.
     * 
     * @param	obj	other customer
     * @return	true (if "equal to") or false
     */
	@Override
	public boolean equals(Object obj) {
		
		Customer customer = (Customer) obj;
		
		if (!this.getFirstName().equals(customer.getFirstName()))
			return false;
		if (!this.getLastName().equals(customer.getLastName()))
			return false;		
		if (!this.getAddress().equals(customer.getAddress()))
			return false;			
		if (!this.getPhone().equals(customer.getPhone()))
			return false;					
		if (!this.getEmail().equals(customer.getEmail()))
			return false;			
		if (!this.getGender().equals(customer.getGender()))
			return false;	
		if (!this.getBirthday().equals(customer.getBirthday()))
			return false;
		if (!Boolean.valueOf(this.isActive()).equals(Boolean.valueOf(customer.isActive())))
			return false;		
		if (!this.getLogin().equals(customer.getLogin()))
			return false;		
		if (!this.getPassword().equals(customer.getPassword()))
			return false;
		if (!Boolean.valueOf(this.isAdmin()).equals(Boolean.valueOf(customer.isAdmin())))
			return false;

		return true;
	}
	
}
