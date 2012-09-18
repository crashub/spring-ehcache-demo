package fr.dr.sandbox.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.dr.sandbox.controller.Customer;

/**
 * Fill the cache at startup.
 * It put a test value in cache (customerId:1, Name:Bob, Address:Bob Address).
 * @author drieu
 *
 */
public class CacheListener implements ServletContextListener {

    @Autowired
    public CacheManager cacheManager;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("========> LOAD CACHE in our listener cache ");
		
		ServletContext servletContext = sce.getServletContext();
		if (null == servletContext) {
		    System.out.println("servlet context is null !");
		}
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (null != ctx) {
            System.out.println("Bean defintion names : " + ctx.getBeanDefinitionNames());

            
            CacheManager cacheManager = (CacheManager) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("cacheManager");
            Cache cache = cacheManager.getCache("customer");
            
            Customer c = new Customer();
            c.setId("1");
            c.setName("Bob");
            c.setAddress("Bob Address");
            cache.put(new Element(c.getId(),c));
            
            Customer customerInCache = (Customer)cache.get("1").getObjectValue();
            System.out.println("========> Name du customer in cache:" + customerInCache.getName());
            
        } else {
            System.out.println("ctx is null !");
        }
        System.out.println("========> FIN LOAD");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	
	
}
