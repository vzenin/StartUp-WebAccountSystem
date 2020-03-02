package com.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is JUnit Test Suite including next JUnit Test Cases:
 * <p> 
 * TestBankDAO, TestAccountDAO, TestCustomerDAO, TestSelectCustomerDAO, TestPaymentDAO
 * @see com.test.TestBankDAO
 * @see com.test.TestAccountDAO
 * @see com.test.TestCustomerDAO
 * @see com.test.TestSelectCustomerDAO
 * @see com.test.TestPaymentDAO
 * @author Victor Zenin
 * @version 1.0.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestBankDAO.class,
				TestAccountDAO.class,
				TestCustomerDAO.class,
				TestSelectCustomerDAO.class,
				TestPaymentDAO.class})
public class AllTests {
}
