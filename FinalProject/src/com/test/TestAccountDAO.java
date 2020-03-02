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

import com.entities.Account;
import com.entities.Bank;
import com.entities.Customer;
import com.service.AccountDAO;
import com.service.AccountDAOI;
import com.service.BankDAO;
import com.service.BankDAOI;
import com.service.CustomerDAO;
import com.service.CustomerDAOI;

/**
 * This is JUnit Test Cases for the class AccountDAO
 * @see com.service.AccountDAO
 * @see com.test.AllTests
 * @see com.test.TestBankDAO
 * @see com.test.TestCustomerDAO
 * @see com.test.TestSelectCustomerDAO
 * @see com.test.TestPaymentDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAccountDAO {

	static Account testingAccount = new Account();
	
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
    * It testing a method createAccount() of an instance of class AccountDAO
    */
	@Test
	public void testA_CreateAccount() {
		BankDAOI bankDAO = new BankDAO();
		CustomerDAOI customerDAO = new CustomerDAO();
		AccountDAOI accountDAO = new AccountDAO();
		Account expectedAccount = new Account();
		Account actualAccount = new Account();
		Bank exBank = new Bank();
		exBank = bankDAO.getBank(1);
		Customer exCustomer = new Customer();
		exCustomer = customerDAO.getCustomer(1);
		expectedAccount.setAccountNumber("123456789");
		expectedAccount.setActive(true);
		expectedAccount.setBalance(100.01);
		expectedAccount.setBank(exBank);
		expectedAccount.setCustomer(exCustomer);
		expectedAccount.setType("Credit");
		testingAccount = expectedAccount;
		accountDAO.createAccount(expectedAccount);
		actualAccount = accountDAO.getAccount(expectedAccount.getAccount_ID());
		assertEquals(expectedAccount,actualAccount);
	}
   /**
    * It testing a method updateAccount() of an instance of class AccountDAO
    */
	@Test
	public void testB_UpdateAccount() {
		AccountDAOI accountDAO = new AccountDAO();
		BankDAOI bankDAO = new BankDAO();
		CustomerDAOI customerDAO = new CustomerDAO();
		Bank exBank = new Bank();
		exBank = bankDAO.getBank(2);
		Customer exCustomer = new Customer();
		exCustomer = customerDAO.getCustomer(2);
		Account expectedAccount = new Account();
		expectedAccount.setAccountNumber("112233445");
		expectedAccount.setActive(false);
		expectedAccount.setBank(exBank);
		expectedAccount.setCustomer(exCustomer);
		expectedAccount.setType("Checking");
		Account actualAccountPrep = new Account();
		actualAccountPrep = accountDAO.getAccount(testingAccount.getAccount_ID());
		accountDAO.updateAccount(actualAccountPrep.getAccount_ID(), expectedAccount);
		Account actualBank = new Account();
		actualBank = accountDAO.getAccount(actualAccountPrep.getAccount_ID());
		testingAccount = actualBank;
		assertEquals(expectedAccount,actualBank);
	}
   /**
    * It testing a method getAccount() of an instance of class AccountDAO
    */
	@Test
	public void testC_GetAccount() {
		AccountDAOI accountDAO = new AccountDAO();
		Account expectedAccount = new Account();
		Account actualAccount = new Account();	
		expectedAccount = testingAccount;
		actualAccount = accountDAO.getAccount(testingAccount.getAccount_ID());
		assertEquals(expectedAccount,actualAccount);
	}
   /**
    * It testing a method deleteAccount() of an instance of class AccountDAO
    */
	@Test
	public void testD_DeleteAccount() {
		AccountDAOI accountDAO = new AccountDAO();
		Account expectedAccount = null;
		Account actualAccount = new Account();		
		accountDAO.deleteAccount(testingAccount.getAccount_ID());	
		actualAccount = accountDAO.getAccount(testingAccount.getAccount_ID());
		assertEquals(expectedAccount,actualAccount);
	}
   /**
    * It testing a method selectAccounts() of an instance of class AccountDAO
    */
	@Test
	public void testE_SelectAccounts() {
		AccountDAOI accountDAO = new AccountDAO();
		List<Account> expectedList = new ArrayList<>();
		List<Account> actualList = new ArrayList<>();
		expectedList.add(accountDAO.getAccount(3));
		expectedList.add(accountDAO.getAccount(2201));
		try {
			actualList = accountDAO.selectAccounts("Select a from Account a where a.account_ID = 3 or a.account_ID = 2201");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expectedList,actualList);
	}
}
