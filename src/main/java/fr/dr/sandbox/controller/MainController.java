package fr.dr.sandbox.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MainController  {


  final Logger logger=Logger.getLogger(getClass().getName()); 

  @Autowired @Qualifier("CustomerService")
  public CustomerService customerService;  
  

  /**
     * Handler de la méthode Get pour l'URL /helloSpringMVC.html. 
     * 
     * @param name le nom que l'on doit afficher dans la vue.
     * @param model une map de toutes les données qui seront utilisables dans la vue 
     * @return le nom de la vue qu'il faudra utiliser.
     */
   @RequestMapping(value="/")
   public  String toIndex(
     @RequestParam(value="name",required=false) String name, 
     ModelMap model) 
   {
  logger.info(">toIndex");
     model.addAttribute("name",name);
     logger.info(">add attribute name " + name);
     
     // on utilisera donc le fichier /WEB-INF/jsp/index.jsp
     //au regard de la stratégie de résolution des vues 
     //utilisée dans cette application.
     return "index";
   }

   




   @RequestMapping(value="/GetCustomer/{customerId}",method={RequestMethod.GET})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody String getCustomer(@PathVariable String customerId){
           Customer c=customerService.getCustomer(customerId);
           return c.toString();
   }


   @RequestMapping(value="/GetCachedCustomer/{customerId}",method={RequestMethod.GET})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody String getCachedCustomer(@PathVariable String customerId){
           Customer c=customerService.getCachedCustomer(customerId);
           return c.toString();
   }

   @RequestMapping(value="/ClearCache",method={RequestMethod.GET})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody String clearCache(){
           boolean result=customerService.clearCache();
           if(result) return "Sucessfully Cache Cleaned";
           else return "Not able to Clean Cache";

   }

   @RequestMapping(value="/SaveCustomer/{customerId}",method={RequestMethod.GET})
   @ResponseStatus(HttpStatus.OK)
   public @ResponseBody String saveCustomer(@PathVariable String customerId){

           Customer c=new Customer();
           c.setId(customerId);
           c.setAddress("New Address");
           c.setName("New Name");
           boolean result=customerService.saveCustomer(c);
           if(result) return "Sucessfully Saved Customer";
           else return "Not able to Save Customer";

   }
   
   

}
