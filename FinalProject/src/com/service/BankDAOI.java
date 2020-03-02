package com.service;

import java.sql.SQLException;
import java.util.List;

import com.entities.Bank;

/**
 * 
 * This is a DAO interface to implements CRUD operations for objects of Bank class
 *
 * @see com.service.AccountDAOI
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
public interface BankDAOI {
   /**
    * Get a list of existing banks from the application
    * @param	sql	JPQL query
    * @return	list of banks
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Bank> selectBanks(String sql) throws SQLException;
   /**
    * Add a new bank to the application
    * @param	newBank	an object, instance of Bank
    */
	public void createBank(Bank newBank);
   /**
    * Update a existing bank in the application
    * @param	bank_ID	ID of Bank
    * @param	newBank	an object, instance of Bank
    */
	public void updateBank(int bank_ID, Bank newBank);
   /**
    * Delete an existing bank in the application
    * @param	bank_ID	ID of Bank
    */
	public void deleteBank(int bank_ID);
   /**
    * Delete an existing bank in the application
    * @param	bank_ID	ID of Bank
    * @return	an object, instance of Bank
    */
	public Bank getBank(int bank_ID);
}
