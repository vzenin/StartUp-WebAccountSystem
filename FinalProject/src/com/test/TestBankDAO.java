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

import com.entities.Bank;
import com.service.BankDAO;
import com.service.BankDAOI;
/**
 * This is JUnit Test Cases for the class BankDAO
 * @see com.service.BankDAO
 * @see com.test.AllTests
 * @see com.test.TestAccountDAO
 * @see com.test.TestCustomerDAO
 * @see com.test.TestSelectCustomerDAO
 * @see com.test.TestPaymentDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBankDAO {
	static Bank testingBank = new Bank();
	
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
    * It testing a method createBank() of an instance of class BankDAO
    */
	@Test
	public void testA_CreateBank() {
		BankDAOI bankDAO = new BankDAO();
		Bank expectedBank = new Bank();
		Bank actualBank = new Bank();
		expectedBank.setRoutingNumber("123454321");
		expectedBank.setName("testNameOfBank");
		expectedBank.setAddress("testAddress");
		expectedBank.setRegion("testRegion");
		testingBank = expectedBank;
		bankDAO.createBank(expectedBank);
		actualBank = bankDAO.getBank(expectedBank.getBank_ID());
		assertEquals(expectedBank,actualBank);
	}
   /**
    * It testing a method updateBank() of an instance of class BankDAO
    */
	@Test
	public void testB_UpdateBank() {
		BankDAOI bankDAO = new BankDAO();
		Bank expectedBank = new Bank();
		expectedBank.setRoutingNumber("112233445");
		expectedBank.setName("updateNameOfBank");
		expectedBank.setAddress("updateAddress");
		expectedBank.setRegion("updateRegion");
		Bank actualBankPrep = new Bank();
		actualBankPrep = bankDAO.getBank(testingBank.getBank_ID());
		bankDAO.updateBank(actualBankPrep.getBank_ID(), expectedBank);
		Bank actualBank = new Bank();
		actualBank = bankDAO.getBank(actualBankPrep.getBank_ID());
		testingBank = actualBank;
		assertEquals(expectedBank,actualBank);
	}
   /**
    * It testing a method getBank() of an instance of class BankDAO
    */
	@Test
	public void testC_GetBank() {
		BankDAOI bankDAO = new BankDAO();
		Bank expectedBank = new Bank();
		Bank actualBank = new Bank();
		expectedBank = testingBank;
		actualBank = bankDAO.getBank(testingBank.getBank_ID());
		assertEquals(expectedBank,actualBank);
	}
   /**
    * It testing a method deleteBank() of an instance of class BankDAO
    */
	@Test
	public void testD_DeleteBank() {
		BankDAOI bankDAO = new BankDAO();
		Bank expectedBank = null;
		Bank actualBank = new Bank();
		bankDAO.deleteBank(testingBank.getBank_ID());	
		actualBank = bankDAO.getBank(testingBank.getBank_ID());
		assertEquals(expectedBank,actualBank);
	}
   /**
    * It testing a method selectBanks() of an instance of class BankDAO
    */
	@Test
	public void testE_SelectBanks() {
		BankDAOI bankDAO = new BankDAO();
		List<Bank> expectedList = new ArrayList<>();
		List<Bank> actualList = new ArrayList<>();
		expectedList.add(bankDAO.getBank(1));
		expectedList.add(bankDAO.getBank(2));
		try {
			actualList = bankDAO.selectBanks("Select b from Bank b "
					+ "where b.bank_ID = 1 or b.bank_ID = 2");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expectedList,actualList);
	}
}
