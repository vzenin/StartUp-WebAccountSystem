/**
 * 
 * com.controller is a group of controllers.
 * 
 */
package com.controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.entities.Account;
import com.entities.Bank;
import com.entities.Customer;
import com.entities.Payment;
import com.exception.SpringException;
import com.service.AccountDAO;
import com.service.BankDAO;
import com.service.CustomerDAO;
import com.service.PaymentDAO;
import com.service.AccountDAOI;
import com.service.BankDAOI;
import com.service.CustomerDAOI;
import com.service.PaymentDAOI;

/**
 * This is a main controller class 
 * @see com.entities.Bank 
 * @see com.entities.Payment
 * @see com.entities.Customer
 * @see com.entities.Account
 * @see com.service.AccountDAO
 * @see com.service.BankDAO
 * @see com.service.CustomerDAO
 * @see com.service.PaymentDAO
 * @author Victor Zenin
 * @version 1.8.
 */
@Controller
@SessionAttributes({"customer"})
public class MainController {
	/**
    * The handler for action = "index"
    * <p>
    * if customer is registered in the application, 
    * there will be transition to a page "index.jsp" (the page: "Home")
    * <p>
    * if customer is not registered in the application, 
    * there will be transition to a page "login.jsp" (the page: "Login page")
    * @param	httpSession	httpSession
    * @return	an object of ModelAndView
    * @throws	SQLException	if JPQL query was executed with an error
    */
	@RequestMapping(value= {"index"})
	public ModelAndView indexHandler(HttpSession httpSession) throws SQLException {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't ?
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) mav.setViewName("login");
    	else {
    		List<Payment> listP;
        	PaymentDAOI paymentDAO = new PaymentDAO();
        	//JPQL query : select all payments of current customer
        	listP = paymentDAO.selectPayments("SELECT p FROM Payment p INNER JOIN p.account a "
        										+ "where p.account.customer.customer_ID = :id order by p.date desc, "
        										+ "p.payment_ID desc", customer.getCustomer_ID());
        	//page has to show only last 10 payments, other payments is deleted from listP
        	for (int i = listP.size()-1; i >= 10; i--)
        		listP.remove(i);
        	mav.addObject("payments", listP);
    		mav.setViewName("index");
    	}
		return mav;
	}
	/**
    * The handler for action = "/login", "", "/"
    * <p>
    * if customer is registered in the application, 
    * there will be redirect to a page "viewaccounts.jsp" (the page: "Accounts list")
    * <p>
    * if customer is not registered in the application, 
    * there will be transition to a page "login.jsp" (the page: "Login page")
    * @param	httpSession	httpSession
    * @return	an object of ModelAndView
    */
	@RequestMapping(value = {"", "/", "/login"})
	public ModelAndView loginHandler(HttpSession httpSession) 
	{		
		//the check : did user sign on in the system or didn't 
		Customer currentCustomer = (Customer) httpSession.getAttribute("customer");
		if (currentCustomer != null) 
			return new ModelAndView("redirect:/viewaccounts");	
		
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}	
	/**
    * The handler for action = "/viewcustomers" 
    * <p>
    * if customer is registered in the application and is administrator, 
    * there will be transition to a page: "viewcustomers.jsp" (the page: "Customers list")
    * <p>
    * if customer is registered in the application and is not administrator, 
    * there will be transition to a page: "index.jsp" (the page: "Home")
    * <p>
    * if customer is not registered in the application, 
    * there will be transition to a page "login.jsp" (the page: "Login page")
    * @param	httpSession	httpSession
    * @return	an object of ModelAndView
    * @throws	SQLException	if JPQL query was executed with an error
    */
    @RequestMapping("/viewcustomers")    
    public ModelAndView viewcustomersHandler(HttpSession httpSession) throws SQLException {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) {
    		mav.setViewName("login");
    		return mav;  
    	}
    	if (customer.isAdmin()) {
    		mav.setViewName("viewcustomers");
    		CustomerDAOI customerDAO = new CustomerDAO();
    		List<Customer> list = customerDAO.selectCustomers("Select c from Customer c"); 
    		mav.addObject("customers", list);
    	}
    	else
    		mav.setViewName("index");
    	
        return mav;    
    }
	/**
     * The handler for action = "/viewbanks" 
     * <p>
     * if customer is registered in the application, 
     * there will be transition to a page: "viewbanks.jsp" (the page: "Banks list")
     * <p>
     * if customer is not registered in the application, 
     * there will be transition to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	httpSession
     * @return	an object of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
    @RequestMapping("/viewbanks")    
    public ModelAndView viewbanksHandler(HttpSession httpSession) throws SQLException {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) {
    		mav.setViewName("login");
    		return mav;  
    	}
    	mav.setViewName("viewbanks");
    	BankDAOI bankDAO = new BankDAO();
    	List<Bank> list = bankDAO.selectBanks("Select b from Bank b");     	
    	mav.addObject("banks", list);
        return mav;    
    }  
	/**
     * The handler for action = "/viewaccounts" 
     * <p>
     * if customer is registered in the application, 
     * there will be transition to a page: "viewaccounts.jsp" (the page: "Accounts list")
     * <p>
     * if customer is not registered in the application, 
     * there will be transition to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	httpSession
     * @return	an object of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
	@RequestMapping("/viewaccounts")    
    public ModelAndView viewAccountsHandler(HttpSession httpSession) throws SQLException {	
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) {
    		mav.setViewName("login");
    		return mav;  
    	}
    	List<Account> listA;
    	AccountDAOI accountDAO = new AccountDAO();
    	if (customer.isAdmin()) {
    		listA = accountDAO.selectAccounts("SELECT a FROM Account a INNER JOIN a.bank b");    		
    	}
    	else
    		listA = accountDAO.selectAccounts("SELECT a FROM Account a INNER JOIN a.bank b "
    								+ "where a.customer.customer_ID = :id", customer.getCustomer_ID());
    	//calculate a common balance
    	double fullBalance = 0.00; 
		for(Account account : listA)
			fullBalance += account.getBalance();
    	
		DecimalFormat formatFullBalance = new DecimalFormat("###,###,###.00");
	    String outputFullBalance = formatFullBalance.format(fullBalance);
	    
    	mav.addObject("accounts", listA);
    	mav.addObject("fullBalance", outputFullBalance);
    	mav.setViewName("viewaccounts");
        return mav;    
    }     
	/**
     * The handler for action = "/viewpayments/{id}" 
     * <p>
     * if customer is registered in the application, 
     * there will be transition to a page: "viewpayments.jsp" (the page: "Payments list")
     * <p>
     * if customer is not registered in the application, 
     * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	httpSession
     * @param	id	PathVariable (id of account)
     * @return	an object of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
	@RequestMapping("/viewpayments/{id}")    
    public ModelAndView viewPaymentsHandler(@PathVariable int id, 
    											HttpSession httpSession) throws SQLException {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else { 
	    	List<Payment> listP;
	    	PaymentDAOI paymentDAO = new PaymentDAO();
	    	//select all payments for one account (using account_ID) 
	    	listP = paymentDAO.selectPayments("SELECT p FROM Payment p INNER JOIN p.account a "
	    			+ "where p.account.account_ID = :id order by p.date desc, p.payment_ID desc", id);
	    	
	    	mav.addObject("payments", listP);
	    	mav.addObject("account", new AccountDAO().getAccount(id));
	    	mav.setViewName("viewpayments");
    	}
        return mav;    
    }
	/**
     * The handler for action = "/deletecustomer/{id}"
     * It deletes record for the given id in URL and redirects to 
     * <p>
	 * if customer is registered in the application and is administrator, 
	 * there will be redirect to a page: "viewcustomers.jsp" (the page: "Customers list")
	 * <p>
	 * if customer is registered in the application and is not administrator, 
	 * there will be redirect to a page: "index.jsp" (the page: "Home")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @param	id	PathVariable (id of customer)
     * @return	an object, instance of ModelAndView
     */
    @RequestMapping(value="/deletecustomer/{id}", method = RequestMethod.GET)    
    public ModelAndView deleteCustomerHandler(@PathVariable int id, 
												HttpSession httpSession) { 
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else { 
    		if (customer.isAdmin()) {
		    	CustomerDAOI customerDAO = new CustomerDAO();    
		    	customerDAO.deleteCustomer(id);    
		    	mav.setViewName("redirect:/viewcustomers");
	    	} else
	    		mav.setViewName("redirect:/index");
    	}
        return mav;
    }  
	/**
     * The handler for action = "/deletebank/{id}"
     * It deletes record for the given id in URL and redirects to 
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewbanks.jsp" (the page: "Banks list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @param	id	PathVariable (id of bank)
     * @return	an object, instance of ModelAndView
     */   
    @RequestMapping(value="/deletebank/{id}",method = RequestMethod.GET)    
    public ModelAndView deleteBankHandler(@PathVariable int id, 
    										HttpSession httpSession){ 
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login"); 
    	else { 
			BankDAOI bankDAO = new BankDAO();
			bankDAO.deleteBank(id);
			mav.setViewName("redirect:/viewbanks");
    	}
        return mav;    
    }
	/**
     * The handler for action = "/deleteaccount/{id}"
     * It deletes record for the given id in URL and redirects to
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewaccounts.jsp" (the page: "Accounts list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @param	id	PathVariable (id of account)
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */ 
    @RequestMapping(value="/deleteaccount/{id}", method = RequestMethod.GET)    
    public ModelAndView deleteAccountHandler(@PathVariable int id, 
    											HttpSession httpSession) throws SQLException{
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) {
    		mav.setViewName("redirect:/login");
    		return mav;  
    	}
    	boolean isErrorViewAccounts = false;
    	PaymentDAOI paymentDAO = new PaymentDAO();
    	AccountDAOI accountDAO = new AccountDAO();
    	
    	if (paymentDAO.selectPayments("SELECT p FROM Payment p "
    			+ "INNER JOIN p.account a where p.account.account_ID = :id "
    			+ "order by p.date desc, p.payment_ID desc", id).size() == 0) {
        	accountDAO.deleteAccount(id);
        	mav.addObject("isErrorViewAccounts", isErrorViewAccounts);
    	} else {
    		isErrorViewAccounts = true;
    		mav.addObject("isErrorViewAccounts", isErrorViewAccounts);
    		mav.addObject("errorMessageViewAccounts", "The account \"" 
    				+ accountDAO.getAccount(id).toStringForViewPaymentsPage() 
    				+ "\" cannot be deleted. The account has transactions.");
    	}
    	mav.setViewName("redirect:/viewaccounts");
        return mav;
    }
	/**
     * The handler for action = "/deletepayment/{id}"
     * It deletes record for the given id in URL and redirects to
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewpayments.jsp" (the page: "Payments list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @param	id	PathVariable (id of payment)
     * @return	an object, instance of ModelAndView
     */   
    @RequestMapping(value="/deletepayment/{id}",method = RequestMethod.GET)    
    public ModelAndView deletePaymentHandler(@PathVariable int id, 
												HttpSession httpSession) {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("redirect:/login"); 
    	else {
	    	PaymentDAOI paymentDAO = new PaymentDAO();
	    	mav.addObject("id", paymentDAO.getPayment(id).getAccount().getAccount_ID());
	    	paymentDAO.deletePayment(id);
	    	mav.setViewName("redirect:/viewpayments/{id}"); 
    	}
        return mav;
    }
	/**
     * The handler for action = "/editcustomer/{id}"
     * It displays to edit record for the given id in URL and redirects to
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "editcustomer.jsp" (the page: "Edit customer")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @param	id	PathVariable (id of customer)
     * @return	an object, instance of ModelAndView
     */    
    @RequestMapping(value = {"/editcustomer/{id}"})   
    public ModelAndView editCustomerIdHandler(@PathVariable int id, 
    												HttpSession httpSession) {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login"); 
    	else {
			CustomerDAOI customerDAO = new CustomerDAO();
			Customer newCustomer = customerDAO.getCustomer(id);  
			mav.addObject("newCustomer", newCustomer);
			mav.setViewName("editcustomer");
    	}
    	return mav;
    }
	/**
     * The handler for action = "/editcustomer"
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "editcustomer.jsp" (the page: "Edit customer")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     */    
    @RequestMapping(value = {"/editcustomer"})   
    public ModelAndView editCustomerHandler(HttpSession httpSession) {  
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
        	CustomerDAOI customerDAO = new CustomerDAO();
        	Customer newCustomer = customerDAO.getCustomer(customer.getCustomer_ID());
        	mav.addObject("newCustomer", newCustomer);
        	mav.setViewName("editcustomer");
    	}
    	return mav;
    }  
	/**
     * The handler for action = "/editsave"
     * <p>
     * if Customer validation has errors, 
	 * there will be transition to a page: "editcustomer.jsp" (the page: "Edit customer")
     * <p>
	 * if customer is registered in the application and is administrator, 
	 * there will be redirect to a page: "viewcustomers.jsp" (the page: "Customers list")
     * <p>
	 * if customer is registered in the application and is not administrator, 
	 * there will be redirect to a page: "index.jsp" (the page: "Home")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	httpSession	HttpSession
     * @param	errors	BindingResult
     * @param	newCustomer	Customer
     * @return	an object, instance of ModelAndView
     */   
    @RequestMapping(value="/editsave", method = RequestMethod.POST)    
    public ModelAndView editSaveHandler(
    		@Valid @ModelAttribute("newCustomer") Customer newCustomer, 
			    								BindingResult errors, 
			    								HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
    	if (errors.hasErrors()) {
    		mav.setViewName("editcustomer");
    		return mav;
    	}
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer != null) {
	    	if (customer.isAdmin())
	    		mav.setViewName("redirect:/viewcustomers");
	    	else
	    		mav.setViewName("redirect:/index");
	    	CustomerDAOI customerDAO = new CustomerDAO();
			customerDAO.updateCustomer(newCustomer.getCustomer_ID(), newCustomer);
    	} else
    		mav.setViewName("redirect:/login");
	    return mav;
    }
	/**
     * The handler for action = "/editbank/{id}"
     * It displays to edit record for the given id in URL and
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "editbank.jsp" (the page: "Edit bank")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	id	PathVariable (id of bank)
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     */ 
    @RequestMapping(value = {"/editbank/{id}"})    
    public ModelAndView editBankIdHandler(@PathVariable int id, 
    										HttpSession httpSession) {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
	    	BankDAOI bankDAO = new BankDAO();
	    	Bank newBank = bankDAO.getBank(id);  
			mav.addObject("command", newBank);
			mav.setViewName("editbank");
    	}
		return mav;
    }
	/**
     * The handler for action = "/savechangesofbank"
     * It saves changes to record and
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewbanks.jsp" (the page: "Banks list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	newBank	an object, instance of Bank
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     */ 
    @RequestMapping(value="/savechangesofbank", method = RequestMethod.POST)    
    public ModelAndView saveChangesOfBankHandler(@ModelAttribute("newBank") Bank newBank,
    												HttpSession httpSession) {  
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
	    	BankDAOI bankDAO = new BankDAO();
	    	bankDAO.updateBank(newBank.getBank_ID(), newBank);    
	    	mav.setViewName("redirect:/viewbanks");    
    	}
    	return mav;
    }
	/**
     * The handler for action = "/editaccount/{id}"
     * It displays to edit record for the given id in URL and
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "editaccount.jsp" (the page: "Edit account")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	id	PathVariable (id of account)
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */     
    @RequestMapping(value = {"/editaccount/{id}"})    
    public ModelAndView editAccountIdHandler(@PathVariable int id, 
    										HttpSession httpSession) throws SQLException{  
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
        	AccountDAOI accountDAO = new AccountDAO();
        	Account newAccount = accountDAO.getAccount(id);  
        	mav.addObject("newAccount", newAccount);
    		mav.addObject("bank", newAccount.getBank());
    		BankDAOI bankDAO = new BankDAO();
            List<Bank> list = bankDAO.selectBanks("Select b from Bank b");
            for (int i = 0; i < list.size(); i++) {
            	if (list.get(i).getBank_ID() == newAccount.getBank().getBank_ID()) {
            		list.remove(i);
            		break;
            	}
            }
        	mav.addObject("banks", list);
        	mav.setViewName("editaccount");
    	}
		return mav;
    }
	/**
     * The handler for action = "/savechangesofaccount"
     * It saves changes to record and
     * <p>
     * if Account validation has errors, 
	 * there will be transition to a page: "editaccount.jsp" (the page: "Edit account")
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewaccounts.jsp" (the page: "Accounts list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	newAccount	Account
     * @param	httpSession	HttpSession
     * @param	errors	BindingResult
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */ 
    @RequestMapping(value="/savechangesofaccount", method = RequestMethod.POST)    
    public ModelAndView saveChangesOfAccountHandler(@Valid @ModelAttribute("newAccount") Account newAccount,
    														BindingResult errors,
    														HttpSession httpSession) throws SQLException {
    	ModelAndView mav = new ModelAndView();
    	if (errors.hasErrors()) {
    		BankDAOI bankDAO = new BankDAO();
    		List<Bank> list = bankDAO.selectBanks("Select b from Bank b"); 
    		mav.addObject("banks", list);
    		mav.setViewName("editaccount");    		
    		return mav;
    	}
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
        	AccountDAOI accountDAO = new AccountDAO();
        	accountDAO.updateAccount(newAccount.getAccount_ID(), newAccount); 
        	mav.setViewName("redirect:/viewaccounts");
    	}
        return mav;
    }
	/**
     * The handler for action = "/editpayment/{id}"
     * It displays to edit record for the given id in URL and
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "editpayment.jsp" (the page: "Edit payment")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	id	PathVariable (id of payment)
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */    
    @RequestMapping(value = {"/editpayment/{id}"})    
    public ModelAndView editPayment(@PathVariable int id, 
    							HttpSession httpSession) throws SQLException {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
	    	PaymentDAOI paymentDAO = new PaymentDAO();
	    	Payment newPayment = paymentDAO.getPayment(id);  
			mav.addObject("command", newPayment);
			mav.setViewName("editpayment");
    	}
		return mav;
    }
	/**
     * The handler for action = "/viewpayment/{id}"
     * It displays record for the given id in URL and
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "viewpayment.jsp" (the page: "Information of the payment")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	id	PathVariable (id of payment)
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */  
    @RequestMapping(value = {"/viewpayment/{id}"})    
    public ModelAndView viewPaymentIdHandler(@PathVariable int id, 
    											HttpSession httpSession) throws SQLException{
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
	    	PaymentDAOI paymentDAO = new PaymentDAO();
	    	Payment payment = paymentDAO.getPayment(id);  
	    	mav.addObject("payment", payment); 
	    	mav.setViewName("viewpayment");
    	}
		return mav;
    }
	/**
     * The handler for action = "/savechangesofpayment"
     * It save changes of payment and 
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "viewpayments.jsp" (the page: "Payments list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	newPayment	Payment
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     */ 
    @RequestMapping(value="/savechangesofpayment", method = RequestMethod.POST)    
    public ModelAndView saveChangesOfPaymentHandler(@ModelAttribute("newPayment") Payment newPayment, 
														HttpSession httpSession) {
    	ModelAndView mav = new ModelAndView();
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
	    	PaymentDAOI paymentDAO = new PaymentDAO();
	    	paymentDAO.updatePayment(newPayment.getPayment_ID(), newPayment);
	    	mav.addObject("id", newPayment.getAccount().getAccount_ID());
	    	mav.setViewName("redirect:/viewpayments/{id}");
    	}
        return mav;    
    }
	/**
     * The handler for action = "/addcustomer"
     * It transitions to a page: "addcustomer.jsp" (the page: "Create new account" or "Add new customer"(for admin role) )
     * @param	mav	ModelAndView
     * @return	an object, instance of ModelAndView
     */ 
    @RequestMapping("/addcustomer")    
    public ModelAndView addCustomerHandler(ModelAndView mav) {    
		mav.addObject("newCustomer", new Customer()); 
		mav.setViewName("addcustomer");
        return mav;   
    }
	/**
     * The handler for action = "/addbank"
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "addbank.jsp" (the page: "Add bank")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	mav	ModelAndView
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     */
    @RequestMapping("/addbank")    
    public ModelAndView addBankHandler(ModelAndView mav, 
    									HttpSession httpSession) {    
    	//the check : did user sign on in the system or didn't 
    	Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("redirect:/login");
    	else {
    		mav.addObject("command", new Bank());
    		mav.setViewName("addbank");
    	}
        return mav;   
    }
	/**
     * The handler for action = "/addaccount"
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "addaccount.jsp" (the page: "Add new account")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
     * @param	mav	ModelAndView
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
    @RequestMapping("/addaccount")  
    public ModelAndView addAccountHandler(ModelAndView mav, 
    										HttpSession httpSession) throws SQLException{
    	//the check : did user sign on in the system or didn't 
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("login");
    	else {
		    BankDAOI bankDAO = new BankDAO();
		    List<Bank> list = bankDAO.selectBanks("Select b from Bank b");     	
			mav.addObject("banks", list);
			mav.addObject("newAccount", new Account());
			mav.setViewName("/addaccount");
    	}
        return mav;  
    }
	/**
     * The handler for action = "/addpayment/{id}" and "/addincome/{id}"
     * It displays record for the given id in URL and
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "addpayment.jsp" (the page: "Add new payment")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	mav	ModelAndView
     * @param	id	PathVariable (id of account)
     * @param	request	HttpServletRequest
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
    @RequestMapping(value = {"/addpayment/{id}", "/addincome/{id}"})  
    public ModelAndView addPaymentIdHandler(@PathVariable int id, 
    									ModelAndView mav,
    									HttpServletRequest request) throws SQLException {
    	//the check : did user sign on in the system or didn't 
        HttpSession httpSession = request.getSession();
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("login");
		else {
	    	AccountDAOI accountDAO = new AccountDAO();
	    	Account account = accountDAO.getAccount(id);
	    	Payment payment = new Payment(account);
	    	String path = request.getServletPath();
	    	if (path.split("/")[1].equals("addpayment"))
	    		payment.setMethod(0);
	    	else {
	        	payment.setMethod(1);
	        	payment.setRecipient("ATM");
	        	payment.setDescription("Income");
	    	}
	        mav.addObject("command", payment);
	    	mav.setViewName("/addpayment");
    	}
        return mav;   
    }
	/**
     * The handler for action = "/createtransactionto/{id}"
     * It displays record of transaction for the given id in URL and
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "createtransactionto.jsp" 
	 * (the page: "Create a transaction to my other accounts")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	mav	ModelAndView
     * @param	id	PathVariable (id of account)
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
    @RequestMapping("/createtransactionto/{id}")  
    public ModelAndView createTransactionToOtherAccountHandler(@PathVariable int id, 
    														ModelAndView mav, 
    														HttpSession httpSession) throws SQLException {
    	//the check : did user sign on in the system or didn't 
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) 
    		mav.setViewName("login");
    	else {
	    	AccountDAOI accountDAO = new AccountDAO();
	    	Account account = accountDAO.getAccount(id);
	    	String sql = "SELECT a FROM Account a INNER JOIN a.bank b "
	    					+ "where a.customer.customer_ID = :id and a.account_ID != :aid";
	        List<Account> listA = accountDAO.selectAccounts(sql, account);     	
	    	mav.addObject("accounts", listA);
	        Payment payment = new Payment(account);
	        payment.setRecipient("-1");
	        mav.addObject("command", payment);
	    	mav.setViewName("/createtransactionto");   	
	    }
        return mav;   
    } 
	/**
     * The handler for action = "/createtransactionbetween"
     * <p>
	 * if customer is registered in the application, 
	 * there will be transition to a page: "createtransactionbetween.jsp" 
	 * (the page: "Create a transaction between my accounts")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	mav	ModelAndView
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
	@RequestMapping("/createtransactionbetween")  
    public ModelAndView createTransactionBetweenAccountsHandler(ModelAndView mav, 
    												HttpSession httpSession) throws SQLException{
    	//the check : did user sign on in the system or didn't 
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("login");
    	else {
	    	String sql = "";
	    	List<Account> listA;
	    	AccountDAOI accountDAO = new AccountDAO();
	    	if (customer.isAdmin()) {
	    		sql = "SELECT a FROM Account a INNER JOIN a.bank b";
	    		listA = accountDAO.selectAccounts(sql);
	    	}
	    	else {
	    		sql = "SELECT a FROM Account a INNER JOIN a.bank b where a.customer.customer_ID = :id";
	    		listA = accountDAO.selectAccounts(sql, customer.getCustomer_ID());
	    	}
	    	mav.addObject("accounts", listA);
	        Payment payment = new Payment();
	        payment.setRecipient("-1");
	        mav.addObject("command", payment);
	    	mav.setViewName("/createtransactionbetween");   
    	}
        return mav;   
    }
	/**
     * The handler for action = "/createnewcustomer"
     * It saves a new customer in the application. 
     * <p>
     * if Customer validation has errors, 
	 * there will be transition to a page: "addcustomer.jsp" (the page: "Add customer")
     * <p>
	 * if customer is registered in the application (it means isAdmin = true, 
	 * because only administrator can see page "Customers List"), 
	 * there will be transition to a page: "viewcustomers.jsp" (the page: "Customers List")
	 * <p>
	 * if customer is not registered in the application (a new user tries to register), 
	 * there will be redirect to a page "viewaccounts.jsp" (the page: "Accounts list")
	 * @param	newCustomer	Customer
     * @param	request	HttpServletRequest
     * @param	errors	BindingResult
     * @return	an object, instance of ModelAndView
     */
    @RequestMapping(value="/createnewcustomer", method = RequestMethod.POST)  
    @ExceptionHandler({SpringException.class})
    public ModelAndView createNewCustomerHandler(@Valid @ModelAttribute("newCustomer") Customer newCustomer, 
    										BindingResult errors,
    										HttpServletRequest request) {  
    	ModelAndView mav = new ModelAndView();    	
    	if (errors.hasErrors())
    		mav.setViewName("addcustomer");    		
    	else {
	    	try {
	    		CustomerDAOI customerDAO = new CustomerDAO();
	    		customerDAO.createCustomer(newCustomer);
	    	} catch(Exception e) {
	    		mav.addObject("isError", true);
	    		mav.addObject("errorMessage", e.getMessage());
	    		mav.setViewName("addcustomer");    		
	    		return mav;
	    	} 
	    	mav.addObject("isError", false);
	    	//the check : did user sign on in the system or didn't 
	    	HttpSession se = request.getSession();
	    	Customer customer = (Customer) se.getAttribute("customer");
	    	if (customer == null) {
				mav.addObject("customer", newCustomer);
	    		mav.setViewName("redirect:/viewaccounts");
	    	} else
	    		mav.setViewName("redirect:/viewcustomers");
	    }
        return mav;
    }
	/**
     * The handler for action = "/createnewbank"
     * It saves a new bank in the application. 
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewbanks.jsp" (the page: "Banks List")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	newBank	Bank
	 * @param	mav	ModelAndView
     * @param	httpSession	HttpSession
     * @return	an object, instance of ModelAndView
     */
    @RequestMapping(value="/createnewbank", method = RequestMethod.POST)    
    public ModelAndView createNewBankHandler(@ModelAttribute("newBank") Bank newBank,
    													ModelAndView mav,
    													HttpSession httpSession){ 
    	//the check : did user sign on in the system or didn't 
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("login");
    	else {
    		BankDAOI bankDAO = new BankDAO();
        	bankDAO.createBank(newBank);
    		mav.setViewName("redirect:/viewbanks");            
    	}
    	return mav;
    }
	/**
     * The handler for action = "/createnewaccount". 
     * It saves a new account in the application. 
     * <p>
     * if account validation has errors, 
	 * there will be transition to a page: "addaccount.jsp" (the page: "Add account")
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewaccounts.jsp" (the page: "Account list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	newAccount	Account
	 * @param	errors	BindingResult
     * @param	httpSession	HttpSession
     * @param	mav	ModelAndView
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
    @RequestMapping(value="/createnewaccount", method = RequestMethod.POST)    
    public ModelAndView createNewAccountHandler(@Valid @ModelAttribute("newAccount") Account newAccount,
    									BindingResult errors,
										HttpSession httpSession,
										ModelAndView mav) throws SQLException {
    	if (errors.hasErrors()) {
    		BankDAOI bankDAO = new BankDAO();
            List<Bank> list = bankDAO.selectBanks("Select b from Bank b"); 
            mav.addObject("newAccount", newAccount); 
        	mav.addObject("banks", list);   		
    		mav.setViewName("addaccount");    		
    		return mav;
    	}
    	if (newAccount.getBank().getBank_ID() < 0) {
    		BankDAOI bankDAO = new BankDAO();
            List<Bank> list = bankDAO.selectBanks("Select b from Bank b");   
            mav.addObject("newAccount", newAccount); 
        	mav.addObject("banks", list);
        	mav.addObject("errorMessage", "You should select a bank.");
        	mav.setViewName("addaccount");
    		return mav;
    	}
    	//the check : did user sign on in the system or didn't 
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("login");
    	else {
	    	AccountDAOI accountDAO = new AccountDAO();
	    	accountDAO.createAccount(newAccount);
			mav.setViewName("redirect:/viewaccounts");  
		}  		
		return mav;
    }
	/**
     * The handler for action = "/createnewpayment". 
     * It saves a new payment in the application. 
     * <p>
     * if account validation has errors, 
	 * there will be transition to a page: "addpayment.jsp" (the page: "Add payment")
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewpayments.jsp" (the page: "Payment list")
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	newPayment	Payment
     * @param	httpSession	HttpSession
     * @param	mav	ModelAndView
     * @return	an object, instance of ModelAndView
     */
    @RequestMapping(value="/createnewpayment", method = RequestMethod.POST)    
    public ModelAndView createNewPaymentHandler(@ModelAttribute("newPayment") Payment newPayment,
    												HttpSession httpSession,
    												ModelAndView mav) {    	
    	//the check : did user sign on in the system or didn't 
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null)
    		mav.setViewName("login");
    	else {    	
	    	boolean isError = false;
	    	AccountDAOI accountDAO = new AccountDAO();
	    	Account account = accountDAO.getAccount(newPayment.getAccount().getAccount_ID());
	    	if ((newPayment.getAmount() > account.getBalance()) && (newPayment.getMethod() == 0)) {
	    		newPayment.setAccount(account);
	    		isError = true;
	    		mav.addObject("isError", isError);
	    		mav.addObject("errorMessage", 
	    				"Amount of payment cannot be biggest that balance of account.");
	    		mav.addObject("command", newPayment);  
	    		mav.setViewName("/addpayment");
	    	} else if (newPayment.getAmount() == 0) {
	    		newPayment.setAccount(account);
	    		isError = true;
	    		mav.addObject("isError", isError);
	    		mav.addObject("errorMessage", 
	    						"Amount of payment cannot be $0.00");
	    		mav.addObject("command", newPayment);  
	    		mav.setViewName("/addpayment");
	    	} else if (newPayment.getDescription().length() > 150) {
	    		newPayment.setAccount(account);
	    		isError = true;
	    		mav.addObject("isError", isError);
	    		mav.addObject("errorMessage", 
	    				"Description of payment cannot be greatest 150 characters");
	    		mav.addObject("command", newPayment);  
	    		mav.setViewName("/addpayment");
	    	} else if ((newPayment.getAmount() > account.getBalance()) && (newPayment.getMethod() == 0)) {
	    		newPayment.setAccount(account);
	    		isError = true;
	    		mav.addObject("isError", isError);
	    		mav.addObject("errorMessage", 
	    				"The amount is bigger  the balance of account");
	    		mav.addObject("command", newPayment);  
	    		mav.setViewName("/addpayment");
	    	} else {
	    		PaymentDAOI paymentDAO = new PaymentDAO();
		    	paymentDAO.createPayment(newPayment);
		    	mav.addObject("id", newPayment.getAccount().getAccount_ID());
		    	mav.setViewName("redirect:/viewpayments/{id}");
	    	}
    	}
        return mav;
    }
	/**
     * The handler for action = "/savetransactionto" and "/savetransactionbetween". 
     * It creates 2 new transactions (debit and credit) between bank accounts of customer in the application. 
     * <p>
     * if account validation has errors, 
	 * there will be transition to a page: "createtransactionbetween.jsp" (the page: "Create a transaction between my accounts")
	 * or "createtransactionto.jsp" (the page: "Create a transaction to my other account") (accordingly)
     * <p>
	 * if customer is registered in the application, 
	 * there will be redirect to a page: "viewaccounts.jsp" (the page: "Accounts list") 
	 * or "viewpayments.jsp" (the page: "Payments list") (accordingly)
	 * <p>
	 * if customer is not registered in the application, 
	 * there will be redirect to a page "login.jsp" (the page: "Login page")
	 * @param	newPayment	Payment
     * @param	request	HttpServletRequest
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
    @RequestMapping(value= {"/savetransactionto","/savetransactionbetween"}, method = RequestMethod.POST)    
    public ModelAndView saveTransactionToAndBetweenHandler(@ModelAttribute("newPayment") Payment newPayment, 
    										HttpServletRequest request) throws SQLException {  
    	ModelAndView mav = new ModelAndView();
    	// the check : did user sign on in the system or didn't age
        HttpSession httpSession = request.getSession();
        Customer customer = (Customer) httpSession.getAttribute("customer");
    	if (customer == null) {
    		mav.setViewName("login");
    		return mav;  
    	}
    	String path = request.getServletPath();
    	boolean flagAccountFrom = true;
    	boolean flagAccountTo = true;
    	boolean flagAmount = true;
    	boolean flagAmountBiggestBalance = true;
    	Account accountTo = null;
    	Account accountFrom = null;
    	// the check: "from" account is filled or not  
    	if(newPayment.getAccount().getAccount_ID() <= 0)
    		flagAccountFrom = false;
    	else {
    		accountFrom = new AccountDAO().getAccount(newPayment.getAccount().getAccount_ID());
    	}
    	// the check: "to" account is filled or not
    	if(Integer.parseInt(newPayment.getRecipient()) <= 0)
    		flagAccountTo = false;	
    	// the check: Amount is filled correctly or not
    	if(newPayment.getAmount() <= 0)
    		flagAmount = false;	        	
    	if((newPayment.getAmount() > accountFrom.getBalance()))
    		flagAmountBiggestBalance = false;
    	// resulting check all of previously check
    	if (flagAccountFrom && flagAccountTo && flagAmount && flagAmountBiggestBalance) {
    		// if all of previously check are true, create transactions (2 debit and credit)
    		PaymentDAOI paymentDAO = new PaymentDAO();
    		paymentDAO.createTransactionTo(newPayment);        	
        	mav.addObject("id", newPayment.getAccount().getAccount_ID());
        	if (path.split("/")[1].equals("savetransactionbetween")) {
        		mav.setViewName("redirect:/viewaccounts");
        	} else {
        		mav.setViewName("redirect:/viewpayments/{id}");
        	}
    	}
    	else {
    		// if 1 or more of previously check are false, 
    		// there are preparing data to return with error message to fill out fields of form on web page
    		AccountDAOI accountDAO = new AccountDAO();
        	String sql = "";        	
            List<Account> listA = null;
    		if (!flagAccountFrom) {
    			mav.addObject("errMsgForAccountFrom", "You should select a FROM account number.");
        		sql = "SELECT a FROM Account a INNER JOIN a.bank b where a.customer.customer_ID = :id";
        		listA = accountDAO.selectAccounts(sql, customer.getCustomer_ID());
        		mav.addObject("accounts", listA);
    		} else {
    			accountFrom = accountDAO.getAccount(newPayment.getAccount().getAccount_ID());
    			mav.addObject("accountFrom", accountFrom);
    			Account account = accountDAO.getAccount(newPayment.getAccount().getAccount_ID());
    			sql = "SELECT a FROM Account a INNER JOIN a.bank b "
    					+ "where a.customer.customer_ID = :id and a.account_ID != :aid";
    			listA = accountDAO.selectAccounts(sql, account);    	
    			mav.addObject("accounts", listA);
    		}
    		if (!flagAccountTo)
    			mav.addObject("errMsgForAccountTo", "You should select a TO account number.");
    		else {
    			accountTo = accountDAO.getAccount(Integer.parseInt(newPayment.getRecipient()));
    			Account accountToDel = accountDAO.getAccount(newPayment.getAccount().getAccount_ID());
    	        for (int i = 0; i < listA.size(); i++) {
    	        	if (listA.get(i).getAccount_ID() == accountToDel.getAccount_ID()) {
    	        		listA.remove(i);
    	        		break;
    	        	}
    	        	mav.addObject("accounts", listA);
    	        }
    		}
    		if (!flagAmount)
    			mav.addObject("errMsgForAmount", "You should fill an amount.");
    		if (!flagAmountBiggestBalance) {
    			mav.addObject("isError", true);
    			mav.addObject("errorMessage", "Amount of transaction cannot be biggest that balance of account.");
    		} else {
    			mav.addObject("isError", false);
    		}
    		mav.addObject("accountTo", accountTo);
    		newPayment.setAccount(accountDAO.getAccount(newPayment.getAccount().getAccount_ID()));
            mav.addObject("command", newPayment);
        	if (path.split("/")[1].equals("savetransactionbetween")) {
        		mav.setViewName("/createtransactionbetween");
        	} else {
        		mav.setViewName("/createtransactionto");
        	}
    	}
        return mav;
    }
	/**
     * The handler for action = "checkLoginPass". 
     * It checks username and password. 
     * <p>
     * if "username and password" validation has errors, 
	 * there will be transition to a page: "login.jsp" (the page: "Login page")
     * <p>
	 * if "username and password" are correct,
	 * there will be redirect to a page:
	 * <p> 
	 * it has a role admin: "viewaccounts.jsp" (the page: "Account list")
	 * <p> 
	 * it doesn't have a role admin: "index.jsp" (the page: "Home")
	 * @param	username	String
     * @param	password	String
     * @return	an object, instance of ModelAndView
     * @throws	SQLException	if JPQL query was executed with an error
     */
	@RequestMapping(value = {"/checkLoginPass"}, method = RequestMethod.POST)
	public ModelAndView checkLoginPassHandler(
			@RequestParam("username") String username,
			@RequestParam("password") String password) throws SQLException {
		boolean isError = false;
		String errorMessage = "The login and/or the password are incorrect";
		ModelAndView mav = new ModelAndView();
		String sql = "SELECT c FROM Customer c WHERE c.login LIKE :custName";
		CustomerDAOI customerDAO = new CustomerDAO();
		List<Customer> listC = customerDAO.selectCustomers(sql, username);
		for(Customer customer : listC) {
			if ((customer.getLogin().equals(username)) && (customer.getPassword().equals(password))) {
				if (customer.isActive()) { 
					if (customer.isAdmin())
						mav.setViewName("redirect:/viewaccounts");
					else
						mav.setViewName("redirect:/index");
					mav.addObject("customer", customer);
					return mav;
				}
				else 
					errorMessage = "The login \"" + customer.getLogin() + "\" is not active";
			}				
		}
		isError = true;
		mav.setViewName("login");
		mav.addObject("errorMessage", errorMessage);
		mav.addObject("isError", isError);
		return mav;
	}
	/**
     * The handler for action = "signout". 
     * It removes object of customer from session "signout".
     * <p>
	 * there will be transition to a page: "login.jsp" (the page: "Login page")
	 * @param	sessionStatus	SessionStatus
     * @return	an object, instance of ModelAndView
     */
	@RequestMapping(value = {"/signout"})
	public ModelAndView SignOutHandler(SessionStatus sessionStatus) {
		ModelAndView mav = new ModelAndView("redirect:/login");
		sessionStatus.setComplete(); 
		return mav;
	}
}