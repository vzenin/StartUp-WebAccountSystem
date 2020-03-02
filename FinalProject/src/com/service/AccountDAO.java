package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entities.Account;

/**
 * 
 * This is a DAO class to execute CRUD operations with objects of Account class
 *
 * @see com.service.BankDAO
 * @see com.service.CustomerDAO
 * @see com.service.PaymentDAO
 * 
 * @author Victor Zenin
 * 
 * @version 1.5.
 * 
 */

public class AccountDAO implements AccountDAOI {
   /**
    * Get a list of existing accounts from the application
    * @param	sql	JPQL query
    * @return	list of accounts
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Account> selectAccounts(String sql) throws SQLException {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    	
    	Query query = entityManager.createQuery(sql);
    	@SuppressWarnings("unchecked")
		List<Account> list = (List<Account>) query.getResultList();
		return list;
	}
   /**
    * Get a list of existing accounts from the application using a filter
    * @param	sql	JPQL query
    * @param	customerID	customer ID
    * @return	list of accounts
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Account> selectAccounts(String sql, int customerID) throws SQLException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    	
    	@SuppressWarnings("unchecked")
    	List<Account> list = entityManager.createQuery(sql)
			    .setParameter("id", customerID)
			    .getResultList();
		return list;
	}
   /**
    * Get a list of existing accounts from the application using filters
    * @param	sql	JPQL query
    * @param	account	an object, instance of Account
    * @return	list of accounts
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Account> selectAccounts(String sql, Account account) throws SQLException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    	
    	@SuppressWarnings("unchecked")
    	List<Account> list = entityManager.createQuery(sql)
    			.setParameter("id", account.getCustomer().getCustomer_ID())
			    .setParameter("aid", account.getAccount_ID())
			    .getResultList();
		return list;
	}	
   /**
    * Add a new account of bank to the application
    * @param	newAccount	an object, instance of Account
    */
	public void createAccount(Account newAccount) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(newAccount);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();	
	}
   /**
    * Delete an existing account of bank in the application
    * @param	account_ID	ID of account
    */
	public void deleteAccount(int account_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Account account = entitymanager.find(Account.class, account_ID);
		entitymanager.remove(account);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
   /**
    * Get an existing account of bank from the application
    * @param	account_ID	ID of account
    * @return	an account of bank in the application
    */
	public Account getAccount(int account_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Account account = entitymanager.find(Account.class, account_ID);
		entitymanager.close();
		emfactory.close();
		return account;
	}
   /**
    * update a existing account of bank in the application
    * <p>
    * Balance of account cannot be changed using this method - updateAccount()
    * @param	account_ID	ID of account
    * @param	newAccount	an new account of bank in the application
    */
	public void updateAccount(int account_ID, Account newAccount) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Account account = entitymanager.find(Account.class, account_ID);		
		account.setAccountNumber(newAccount.getAccountNumber());
		account.setActive(newAccount.isActive());
		account.setType(newAccount.getType());
		
		CustomerDAO customerDAO = new CustomerDAO();
		account.setCustomer(customerDAO.getCustomer(newAccount.getCustomer().getCustomer_ID()));
		
		BankDAO bankDAO = new BankDAO();
		account.setBank(bankDAO.getBank(newAccount.getBank().getBank_ID()));
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
}
