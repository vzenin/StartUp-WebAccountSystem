package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entities.Bank;

/**
 * 
 * This is a DAO class to execute CRUD operations with objects of Bank class
 *
 * @see com.service.AccountDAO
 * @see com.service.CustomerDAO
 * @see com.service.PaymentDAO
 * 
 * @author Victor Zenin
 * 
 * @version 1.5.
 * 
 */
public class BankDAO implements BankDAOI{
   /**
    * Get a list of existing banks from the application
    * @param	sql	JPQL query
    * @return	list of banks
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Bank> selectBanks(String sql) throws SQLException {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    	
    	Query query = entityManager.createQuery(sql);
    	@SuppressWarnings("unchecked")
		List<Bank> list = (List<Bank>)query.getResultList();		
		return list;
	}
   /**
    * Add a new bank to the application
    * @param	newBank	an object, instance of Bank
    */
	public void createBank(Bank newBank) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(newBank);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();	
	}
   /**
    * Update a existing bank in the application
    * @param	bank_ID	ID of Bank
    * @param	newBank	an object, instance of Bank
    */
	public void updateBank(int bank_ID, Bank newBank) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Bank bank = entitymanager.find(Bank.class, bank_ID);
		bank.setRoutingNumber(newBank.getRoutingNumber());
		bank.setName(newBank.getName());
		bank.setAddress(newBank.getAddress());
		bank.setRegion(newBank.getRegion());
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
   /**
    * Delete an existing bank in the application
    * @param	bank_ID	ID of Bank
    */
	public void deleteBank(int bank_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Bank bank = entitymanager.find(Bank.class, bank_ID);
		entitymanager.remove(bank);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
   /**
    * Delete an existing bank in the application
    * @param	bank_ID	ID of Bank
    * @return	an object, instance of Bank
    */
	public Bank getBank(int bank_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Bank bank = entitymanager.find(Bank.class, bank_ID);
		entitymanager.close();
		emfactory.close();
		return bank;
	}
}
