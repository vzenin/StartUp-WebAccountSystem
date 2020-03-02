package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entities.Customer;
import com.exception.CreateCustomerException;

/**
 * 
 * This is a DAO class to execute CRUD operations with objects of Customer class
 *
 * @see com.service.BankDAO
 * @see com.service.AccountDAO
 * @see com.service.PaymentDAO
 * 
 * @author Victor Zenin
 * 
 * @version 1.5.
 * 
 */
public class CustomerDAO implements CustomerDAOI{
   /**
    * Get a list of existing customers from the application
    * @param	sql	JPQL query
    * @return	list of customers
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Customer> selectCustomers(String sql) throws SQLException {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    
    	
    	Query query = entityManager.createQuery(sql);
    	@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) query.getResultList();	
    	
    	entityManager.close();
    	entityManagerFactory.close();
		return list;
	}
   /**
    * Get a list of existing customers from the application
    * @param	sql	JPQL query
    * @param	username	login of customer
    * @return	list of customers
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public List<Customer> selectCustomers(String sql, String username) throws SQLException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FinalProject");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();    	
    	@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) entityManager.createQuery(sql)
			    								.setParameter("custName", username)
			    								.getResultList();
    	entityManager.close();
    	entityManagerFactory.close();
		return list;
	}
   /**
    * Add a new customer to the application
    * @param	newCustomer	an object, instance of Customer
    * @throws	CreateCustomerException	if the system cannot create the new user. An user exists already with the same login
    * @throws	SQLException	if JPQL query was executed with an error
    */
	public void createCustomer(Customer newCustomer) throws CreateCustomerException, SQLException {
		String sql = "SELECT c FROM Customer c WHERE c.login LIKE :custName";
		List<Customer> listC = selectCustomers(sql, newCustomer.getLogin());		
		if (listC.size()>0)
			throw new CreateCustomerException("The system cannot create the new user. "
					+ "An user exists already with the same login.");
		else {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(newCustomer);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			emfactory.close();	
		}
	}
   /**
    * Update a existing customer in the application
    * @param	customer_ID	ID of customer
    * @param	newcustomer	an object, instance of Customer
    */
	public void updateCustomer(int customer_ID, Customer newcustomer) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "FinalProject" );
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
  
		Customer customer = entitymanager.find( Customer.class, customer_ID);
		customer.setFirstName(newcustomer.getFirstName());
		customer.setLastName(newcustomer.getLastName());
		customer.setAddress(newcustomer.getAddress());
		customer.setPhone(newcustomer.getPhone());					
		customer.setEmail(newcustomer.getEmail());
		customer.setGender(newcustomer.getGender());
		customer.setBirthday(newcustomer.getBirthday());
		customer.setActive(newcustomer.isActive());
		customer.setLogin(newcustomer.getLogin());
		customer.setPassword(newcustomer.getPassword());
		customer.setAdmin(newcustomer.isAdmin());
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
   }
   /**
    * Delete an existing customer in the application
    * @param	customer_ID	ID of customer
    */
	public void deleteCustomer(int customer_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Customer customer = entitymanager.find(Customer.class, customer_ID);
		entitymanager.remove(customer);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
   /**
    * Get an existing customer from the application
    * @param	customer_ID	ID of customer
    * @return	an object, instance of Customer
    */
	public Customer getCustomer(int customer_ID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FinalProject");
		EntityManager entitymanager = emfactory.createEntityManager();
		Customer customer = entitymanager.find(Customer.class, customer_ID);
		entitymanager.close();
		emfactory.close();
		return customer;
	}
}