package com.service;

import java.sql.SQLException;
import java.util.List;

import com.entities.Account;

/**
 * 
 * This is a DAO interface to implements CRUD operations for objects of Account class
 *
 * @see com.service.BankDAOI
 * @see com.service.CustomerDAOI
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
public interface AccountDAOI {
   /**
    * Get a list of existing accounts from the application
    * @param	sql	JPQL query
    * @return	list of accounts
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Account> selectAccounts(String sql) throws SQLException;
   /**
    * Get a list of existing accounts from the application using a filter
    * @param	sql	JPQL query
    * @param	customerID	customer ID
    * @return	list of accounts
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Account> selectAccounts(String sql, int customerID) throws SQLException;
   /**
    * Get a list of existing accounts from the application using filters
    * @param	sql	JPQL query
    * @param	account	an object, instance of Account
    * @return	list of accounts
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Account> selectAccounts(String sql, Account account) throws SQLException;
   /**
    * Add a new account of bank to the application
    * @param	newAccount	an object, instance of Account
    */
	public void createAccount(Account newAccount);
   /**
    * Delete an existing account of bank in the application
    * @param	account_ID	ID of account
    */
	public void deleteAccount(int account_ID);
   /**
    * Get an existing account of bank from the application
    * @param	account_ID	ID of account
    * @return	an account of bank in the application
    */
	public Account getAccount(int account_ID);	
   /**
    * update a existing account of bank in the application
    * <p>
    * Balance of account cannot be changed using this method - updateAccount()
    * @param	account_ID	ID of account
    * @param	newAccount	an new account of bank in the application
    */
	public void updateAccount(int account_ID, Account newAccount);
}
