package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entities.Payment;

/**
 * 
 * This is a DAO class to execute CRUD operations with objects of Payment class
 *
 * @see com.service.BankDAO
 * @see com.service.CustomerDAO
 * @see com.service.AccountDAO
 * 
 * @author Victor Zenin
 * 
 * @version 1.5.
 * 
 */
public class PaymentDAO implements PaymentDAOI{
   /**
    * Get a list of existing payments to the application
    * @param	sql	JPQL query
    * @param	id	ID for filter of query
    * @return	list of payments
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Payment> selectPayments(String sql, int id) throws SQLException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    	
    	@SuppressWarnings("unchecked")
    	List<Payment> list = entityManager.createQuery(sql)
    										.setParameter("id", id)
    										.getResultList();
		return list;
	}
   /**
    * Add a new payment to the application
    * @param	newPayment	an object, instance of Payment
    */
	public void createPayment(Payment newPayment) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
    	//possible values: 0 - credit, 1 - debit
    	if (newPayment.getMethod() == 0)
    		//if 0 - credit, that multiple by -1
    		newPayment.setAmount(-1*Math.abs(newPayment.getAmount()));
    	else
    		//if 1 - debit, that make math.abs()
    		newPayment.setAmount(Math.abs(newPayment.getAmount()));
		entitymanager.persist(newPayment);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();	
	}
   /**
    * Add 2 new payments (debit / credit) to the application
    * @param	fromPayment	an object, instance of Payment
    */
	public void createTransactionTo(Payment fromPayment) {
		AccountDAO accountDAO = new AccountDAO();
    	fromPayment.setAccount(accountDAO.getAccount(fromPayment.getAccount().getAccount_ID()));
    	Payment toPayment = new Payment(accountDAO.getAccount(Integer.parseInt(fromPayment.getRecipient())));
    	fromPayment.setAmount(-1*Math.abs(fromPayment.getAmount()));
    	toPayment.setAmount(fromPayment.getAmount());
    	toPayment.setAmount(Math.abs(fromPayment.getAmount()));
    	toPayment.setDate(fromPayment.getDate());
    	toPayment.setMethod(1);
    	fromPayment.setRecipient("account number " + toPayment.getAccount().getAccountNumber());
    	toPayment.setRecipient("Income from account number " + fromPayment.getAccount().getAccountNumber());
    	fromPayment.setDescription("transaction from account number " 
    								+ fromPayment.getAccount().getAccountNumber()
    								+ " to account number " 
    								+ toPayment.getAccount().getAccountNumber());
    	toPayment.setDescription(fromPayment.getDescription());
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(fromPayment);
		entitymanager.persist(toPayment);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();	
	}
   /**
    * Delete an existing payment in the application
    * @param	payment_ID	ID of payment
    */
	public void deletePayment(int payment_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Payment payment = entitymanager.find(Payment.class, payment_ID);
		entitymanager.remove(payment);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
   /**
    * Get an existing payment from the application
    * @param	payment_ID	ID of payment
    */
	public Payment getPayment(int payment_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Payment payment = entitymanager.find(Payment.class, payment_ID);
		entitymanager.close();
		emfactory.close();
		return payment;
	}
   /**
    * Update a existing payment in the application
    * @param	payment_ID	ID of payment
    * @param	newPayment	an new payment in the application
    */
	public void updatePayment(int payment_ID, Payment newPayment) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Payment payment = entitymanager.find(Payment.class, payment_ID);
		AccountDAO accountDAO = new AccountDAO();
		payment.setAccount(accountDAO.getAccount(newPayment.getAccount().getAccount_ID()));
    	//possible values: 0 - credit, 1 - debit
    	if (newPayment.getMethod() == 0)
    		//if 0 - credit, that multiple by -1
    		payment.setAmount(-1*Math.abs(newPayment.getAmount()));
    	else
    		//if 1 - debit, that make math.abs()
    		payment.setAmount(Math.abs(newPayment.getAmount()));
		payment.setDate(newPayment.getDate());
		payment.setDescription(newPayment.getDescription());
		payment.setMethod(newPayment.getMethod());
		payment.setRecipient(newPayment.getRecipient());
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
}
