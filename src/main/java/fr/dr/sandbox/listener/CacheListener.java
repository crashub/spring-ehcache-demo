package fr.dr.sandbox.listener;

import javax.servlet.*;

import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;

public class CacheListener implements ServletContextListener {

    @Autowired
    public CacheManager cacheManager;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("LOAD");
		ServletContext servletContext = sce.getServletContext();
		System.out.println("Servlet info :" + servletContext.toString());
        servletContext.setAttribute("mycache", cacheManager);
        System.out.println("FIN LOAD");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	
	
}
