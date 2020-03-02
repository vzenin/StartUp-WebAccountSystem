package com.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.entities.Customer;
import com.service.CustomerDAO;
import com.service.CustomerDAOI;
/**
 * This is JUnit Test Cases for the class CustomerDAO
 * @see com.service.CustomerDAO
 * @see com.test.AllTests
 * @see com.test.TestAccountDAO
 * @see com.test.TestBankDAO
 * @see com.test.TestCustomerDAO
 * @see com.test.TestPaymentDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSelectCustomerDAO {

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
    * It testing a method selectCustomers(String) of an instance of class CustomerDAO
    */
	@Test
	public void testE_SelectCustomersString() {
		CustomerDAOI customerDAO = new CustomerDAO();
		List<Customer> expectedList = new ArrayList<>();
		List<Customer> actualList = new ArrayList<>();
		expectedList.add(customerDAO.getCustomer(1));
		expectedList.add(customerDAO.getCustomer(2));
		try {
			actualList = customerDAO.selectCustomers("Select c from Customer c where c.customer_ID = 1 or c.customer_ID = 2");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expectedList, actualList);
	}
   /**
    * It testing a method selectCustomers(String, String) of an instance of class CustomerDAO
    */
	@Test
	public void testF_SelectCustomersString() {
		CustomerDAOI customerDAO = new CustomerDAO();
		List<Customer> expectedList = new ArrayList<>();
		List<Customer> actualList = new ArrayList<>();
		expectedList.add(customerDAO.getCustomer(1));
		try {
			actualList = customerDAO.selectCustomers("Select c from Customer c"
					+ " WHERE c.login LIKE :custName", 
					customerDAO.getCustomer(1).getLogin());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expectedList, actualList);	
	}
}
