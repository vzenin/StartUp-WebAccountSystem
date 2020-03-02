package com.service;

import java.sql.SQLException;
import java.util.List;

import com.entities.Payment;

/**
 * 
 * This is a DAO interface to implements CRUD operations for objects of Payment class
 *
 * @see com.service.BankDAOI
 * @see com.service.CustomerDAOI
 * @see com.service.AccountDAOI
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
public interface PaymentDAOI {
   /**
    * Get a list of existing payments to the application
    * @param	sql	JPQL query
    * @param	id	ID for filter of query
    * @return	list of payments
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Payment> selectPayments(String sql, int id) throws SQLException;
   /**
    * Add a new payment to the application
    * @param	newPayment	an object, instance of Payment
    */
	public void createPayment(Payment newPayment);
   /**
    * Add 2 new payments (debit / credit) to the application
    * @param	fromPayment	an object, instance of Payment
    */
	public void createTransactionTo(Payment fromPayment);
   /**
    * Delete an existing payment in the application
    * @param	payment_ID	ID of payment
    */
	public void deletePayment(int payment_ID);
   /**
    * Get an existing payment from the application
    * @param	payment_ID	ID of payment
    * @return	an object, instance of Payment
    */
	public Payment getPayment(int payment_ID);
   /**
    * Update a existing payment in the application
    * @param	payment_ID	ID of payment
    * @param	newPayment	an new payment in the application
    */
	public void updatePayment(int payment_ID, Payment newPayment);
}
