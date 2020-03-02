package com.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.entities.Account;
import com.entities.Payment;
import com.service.AccountDAO;
import com.service.AccountDAOI;
import com.service.PaymentDAO;
import com.service.PaymentDAOI;
/**
 * This is JUnit Test Cases for the class PaymentDAO
 * @see com.service.PaymentDAO
 * @see com.test.AllTests
 * @see com.test.TestAccountDAO
 * @see com.test.TestBankDAO
 * @see com.test.TestSelectCustomerDAO
 * @see com.test.TestCustomerDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPaymentDAO {

	Payment expectedPayment;
	Integer expectedNumber;
   /**
    * Constructor sets index number and expected payment
    * @param	i	Integer
    * @param	p	Payment (expectedPayment)
    */
	public TestPaymentDAO(Integer i, Payment p) {
		  this.expectedPayment = p;
		  this.expectedNumber = i;
	}
   /**
    * It returns collection consist from index numbers and expected payments
    * @return	Collection of index numbers and expected payments
    */
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection params() {
		AccountDAOI accountDAO = new AccountDAO(); 
		Account account = accountDAO.getAccount(3);
		return Arrays.asList(new Object[][] {
			{1, new Payment(0, 
					account,
					"1900-01-01",
					"test1_Recipient",
					1.23,
					0,
					"test1_Description")},
			{2, new Payment(0, 
					account,
					"1900-02-02",
					"test2_Recipient",
					2.34,
					1,
					"test2_Description")}
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
    * It testing a method createPayment() of an instance of class PaymentDAO
    */
	@Test
	public void testA_CreatePayment() {
		PaymentDAOI paymentDAO = new PaymentDAO(); 
		paymentDAO.createPayment(expectedPayment);
		Payment actualPayment = paymentDAO.getPayment(expectedPayment.getPayment_ID());
		assertEquals(expectedPayment, actualPayment);
	}
   /**
    * It testing a method updatePayment() of an instance of class PaymentDAO
    */
	@Test
	public void testB_UpdatePayment() {
		PaymentDAOI paymentDAO = new PaymentDAO();
		expectedPayment.setDate("190" + expectedNumber.toString() 
										+ "-0" + expectedNumber.toString() 
										+ "-0" + expectedNumber.toString());
		expectedPayment.setRecipient("updateRecipient" + expectedNumber.toString());
		if (expectedPayment.getMethod() == 0)
			expectedPayment.setAmount(-1*expectedNumber);
		else
			expectedPayment.setAmount(expectedNumber);
		expectedPayment.setDescription("updateDescription" + expectedNumber.toString());
		Payment actualPaymentPrep = new Payment();
		actualPaymentPrep = paymentDAO.getPayment(expectedPayment.getPayment_ID());
		paymentDAO.updatePayment(actualPaymentPrep.getPayment_ID(), expectedPayment);
		Payment actualPayment = new Payment();
		actualPayment = paymentDAO.getPayment(expectedPayment.getPayment_ID());
		assertEquals(expectedPayment,actualPayment);
	}
   /**
    * It testing a method getPayment() of an instance of class PaymentDAO
    */
	@Test
	public void testC_GetPayment() {
		PaymentDAOI paymentDAO = new PaymentDAO();
		Payment actualPayment = new Payment();
		actualPayment = paymentDAO.getPayment(expectedPayment.getPayment_ID());
		assertEquals(expectedPayment,actualPayment);
	}
   /**
    * It testing a method selectPayments() of an instance of class PaymentDAO
    */
	@Test
	public void testE_SelectPayments() {
		PaymentDAOI paymentDAO = new PaymentDAO(); 
		List<Payment> expectedList = new ArrayList<>();
		List<Payment> actualList = new ArrayList<>();
		expectedList.add(paymentDAO.getPayment(expectedPayment.getPayment_ID()));
		try {
			actualList = paymentDAO.selectPayments("Select p from Payment p "
					+ "where p.payment_ID = :id", expectedPayment.getPayment_ID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expectedList,actualList);	
	}
	/**
    * It testing a method deletePayment() of an instance of class PaymentDAO
    */
	@Test
	public void testF_DeletePayment() {
		PaymentDAOI paymentDAO = new PaymentDAO();
		Payment actualPayment = new Payment();
		paymentDAO.deletePayment(expectedPayment.getPayment_ID());	
		actualPayment = paymentDAO.getPayment(expectedPayment.getPayment_ID());		
		assertEquals(null, actualPayment);
	}
}
