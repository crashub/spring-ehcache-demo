package fr.dr.sandbox.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.dr.sandbox.controller.Customer;

/**
 * Fill the cache at startup.
 * It put a test value in cache (customerId:1, Name:Smith, Address:Smith Address).
 * @author drieu
 *
 */
public class CacheListener implements ServletContextListener {

	final Logger logger = Logger.getLogger(getClass().getName());
	
    @Autowired
    public CacheManager cacheManager;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("========> contextInitialized() : BEGIN. ");
		
		ServletContext servletContext = sce.getServletContext();
		if (null == servletContext) {
		    logger.warn("servlet context is null !");
		}
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (null != ctx) {
            
            CacheManager cacheManager = (CacheManager) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("cacheManager");
            Cache cache = cacheManager.getCache("customer");
            
            Customer c = new Customer();
            c.setId("1");
            c.setName("Smith");
            c.setAddress("Smith Address");
            cache.put(new Element(c.getId(),c));
            
            Customer customerInCache = (Customer)cache.get("1").getObjectValue();
            logger.info("========> contextInitialized() : Customer " + customerInCache.getName() + " was added in cache.");
            
        } else {
        	logger.warn("ctx is null !");
        }
        logger.info("========> contextInitialized() : END");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	
	
}
