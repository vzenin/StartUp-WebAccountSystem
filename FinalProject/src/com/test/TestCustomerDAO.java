package com.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.entities.Customer;
import com.exception.CreateCustomerException;
import com.service.CustomerDAO;
import com.service.CustomerDAOI;
/**
 * This is JUnit Test Cases for the class CustomerDAO
 * @see com.service.CustomerDAO
 * @see com.test.AllTests
 * @see com.test.TestAccountDAO
 * @see com.test.TestBankDAO
 * @see com.test.TestSelectCustomerDAO
 * @see com.test.TestPaymentDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCustomerDAO {

	Customer expectedCustomer;
	Integer expectedNumber;
   /**
    * Constructor sets index number and expected customer
    * @param	i	Integer
    * @param	c	Customer (expectedCustomer)
    */
	public TestCustomerDAO(Integer i, Customer c) {
		  this.expectedCustomer = c;
		  this.expectedNumber = i;
	} 
   /**
    * It returns collection consist from index numbers and expected customers
    * @return	Collection of index numbers and expected customers
    */
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{1, new Customer(0, "test1_FirstName",
							"test1_lastName",
							"test1_address", 
							"100-000-0000",
							"test1@email.com",
							"male",
							"2020-01-01", 
							true, 
							"test1_login",
							"test1_pass",
							true)},
			{2, new Customer(0, "test2_FirstName",
							"test2_lastName",
							"test2_address", 
							"200-000-0000",
							"test2@email.com",
							"male",
							"2020-02-02", 
							false, 
							"test2_login",
							"test2_pass",
							false)}
		}); 
	}
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
   /**
    * It testing a method createCustomer() of an instance of class CustomerDAO
    * @throws	SQLException	if JPQL query was executed with an error
    * @throws	CreateCustomerException	if the system cannot create the new user. An user exists already with the same login
    */
	@Test
	public void testA_CreateCustomer() throws CreateCustomerException, SQLException {
		CustomerDAOI customerDAO = new CustomerDAO();
		Customer actualCustomer = new Customer();		
		customerDAO.createCustomer(expectedCustomer);
		actualCustomer = customerDAO.getCustomer(expectedCustomer.getCustomer_ID());
		assertEquals(expectedCustomer, actualCustomer);
	}
   /**
    * It testing a method updateCustomer() of an instance of class CustomerDAO
    */
	@Test
	public void testB_UpdateCustomer() {
		CustomerDAOI customerDAO = new CustomerDAO();
		expectedCustomer.setFirstName("update" + expectedNumber.toString() + "_FirstName");
		expectedCustomer.setLastName("update" + expectedNumber.toString() + "_LastName");
		expectedCustomer.setAddress("update" + expectedNumber.toString() + "_address");
		expectedCustomer.setPhone("0" + expectedNumber.toString() + "0-000-0000");
		expectedCustomer.setEmail("update" + expectedNumber.toString() + "@email.com");
		expectedCustomer.setGender("female");
		expectedCustomer.setBirthday("1919-0" + expectedNumber.toString() + "-0" + expectedNumber.toString());
		expectedCustomer.setActive(false);
		expectedCustomer.setLogin("update" + expectedNumber.toString() + "_login");
		expectedCustomer.setPassword("update" + expectedNumber.toString() + "_pass");
		expectedCustomer.setAdmin(false);
		Customer actualCustomerPrep = new Customer();
		actualCustomerPrep = customerDAO.getCustomer(expectedCustomer.getCustomer_ID());
		customerDAO.updateCustomer(actualCustomerPrep.getCustomer_ID(), expectedCustomer);
		Customer actualCustomer = new Customer();
		actualCustomer = customerDAO.getCustomer(actualCustomerPrep.getCustomer_ID());
		assertEquals(expectedCustomer, actualCustomer);
	}
   /**
    * It testing a method getCustomer() of an instance of class CustomerDAO
    */
	@Test
	public void testC_GetCustomer() {
		CustomerDAOI customerDAO = new CustomerDAO();
		Customer actualCustomer = new Customer();
		actualCustomer = customerDAO.getCustomer(expectedCustomer.getCustomer_ID());
		assertEquals(expectedCustomer, actualCustomer);
	}
   /**
    * It testing a method deleteCustomer() of an instance of class CustomerDAO
    */
	@Test
	public void testD_DeleteCustomer() {
		CustomerDAOI customerDAO = new CustomerDAO();
		Customer actualCustomer = new Customer();
		customerDAO.deleteCustomer(expectedCustomer.getCustomer_ID());	
		actualCustomer = customerDAO.getCustomer(expectedCustomer.getCustomer_ID());
		assertEquals(null, actualCustomer);
	}
}
