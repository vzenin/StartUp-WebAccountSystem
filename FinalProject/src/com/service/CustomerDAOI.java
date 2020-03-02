package com.service;

import java.sql.SQLException;
import java.util.List;

import com.entities.Customer;
import com.exception.CreateCustomerException;

/**
 * 
 * This is a DAO interface to implements CRUD operations for objects of Customer class
 *
 * @see com.service.BankDAOI
 * @see com.service.AccountDAOI
 * @see com.service.PaymentDAOI
 * 
 * @see com.service.BankDAO
 * @see com.service.CustomerDAO
 * @see com.service.PaymentDAO
 * @see com.service.AccountDAO
 * 
 * @author Victor Zenin
 * 
 * @version 1.0.
 * 
 */
public interface CustomerDAOI {
   /**
    * Get a list of existing customers from the application
    * @param	sql	JPQL query
    * @return	list of customers
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Customer> selectCustomers(String sql) throws SQLException;
   /**
    * Get a list of existing customers from the application
    * @param	sql	JPQL query
    * @param	username	login of customer
    * @return	list of customers
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Customer> selectCustomers(String sql, String username) throws SQLException;
   /**
    * Add a new customer to the application
    * @param	newCustomer	an object, instance of Customer
    * @throws	CreateCustomerException	if the system cannot create the new user. An user exists already with the same login
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public void createCustomer(Customer newCustomer) throws CreateCustomerException, SQLException;
   /**
    * Update a existing customer in the application
    * @param	customer_ID	ID of customer
    * @param	newcustomer	an object, instance of Customer
    */
	public void updateCustomer(int customer_ID, Customer newcustomer);
   /**
    * Delete an existing customer in the application 
    * @param	customer_ID	ID of customer
    */
	public void deleteCustomer(int customer_ID);
   /**
    * Get an existing customer from the application
    * @param	customer_ID	ID of customer
    * @return	an object, instance of Customer
    */
	public Customer getCustomer(int customer_ID);
	
}
