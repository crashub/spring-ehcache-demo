package fr.dr.sandbox.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller.
 * @author drieu
 *
 */
@Controller
public class MainController {

	final Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	@Qualifier("CustomerComponent")
	public CustomerComponent customerComponent;

	/**
	 * Main page of this application.
	 * 
	 * @param name Use when we say Hello.
	 * @param model ModelMap.
	 * @return view name.
	 */
	@RequestMapping(value = "/")
	public String toIndex(
			@RequestParam(value = "name", required = false) String name,
			ModelMap model) {
		model.addAttribute("name", name);
		model.addAttribute("customers", customerComponent.getCache());
		return "index";
	}

	/**
	 * Clear all data in cache.
	 * @return String text message OK or KO.
	 */
	@RequestMapping(value = "/clearCache", method = { RequestMethod.GET })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	String clearCache() {
		customerComponent.clearCache();
		return "Cache successfully Cleaned";

	}
	
	/**
	 * Access to the page to add a Customer in cache.
	 * @param model Model
	 * @return "add" page.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String questionnaire(final Model model) {
		final Customer customer = new Customer();
		model.addAttribute("premierFormulaire", customer);
		return "add";
	}

	/**
	 * Save a Customer.
	 * @param customer Customer
	 * @param model Model
	 * @return "success" or "failed" page.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String validation(
			@ModelAttribute("premierFormulaire") final Customer customer,
			final Model model) {
		model.addAttribute("retour",
				customer.getId() + " " + customer.getName());
		boolean result = customerComponent.saveCustomer(customer);
		if (result) {
			return "success";
		} else {
			return "failed";
		}
	}

}
