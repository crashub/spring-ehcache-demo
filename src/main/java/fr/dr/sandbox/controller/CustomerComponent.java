package fr.dr.sandbox.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.TriggersRemove;

@Component("CustomerComponent")
public class CustomerComponent {
        
	
		final Logger logger = Logger.getLogger(getClass().getName());
	
        @Autowired
        public CacheManager cacheManager;
        
        @Qualifier(value="customer")
        public void setMyCache(final CacheManager cache) {
            cacheManager = cache;
        }
        
        /**
         * Save a Customer in cache.
         * @param customer Customer.
         * @return
         */
        public boolean saveCustomer(Customer customer){
                Cache cache=cacheManager.getCache("customer");
                cache.put(new Element(customer.getId(),customer));
                return true;
        }
        
        /**
         * Remove all data in cache.
         * @return boolean 
         */
        @TriggersRemove(cacheName = "customer", removeAll = true) 
        public void clearCache(){
        }
        
        /**
         * Retrieve a list of Customer in cache.
         * @return List<Customer>.
         */
    	public List<Customer> getCache() {
    		List<Customer> lst = new ArrayList<Customer>();
            Cache cache=cacheManager.getCache("customer");
            
            List<String> l = cache.getKeys();
            Customer customer = null;
            for(String customerId : l) {
                    Element el = (Element)cache.get(customerId);
                    if (null != el) {
	                    customer = (Customer)el.getValue();
	                    if (null != customer) {
	                    	logger.info("Customer id :" + customer.getId());
	                    	logger.info("Customer name :" + customer.getName());
	                    	lst.add(customer);
	                    }
                    }
            }
            return lst;
    	}
    	
    	/**
    	 * Demo Method to Print a message.
    	 */
    	public static void show() {
    		System.out.println("You call a static method of CustomerComponent !");
    	}    	
}

