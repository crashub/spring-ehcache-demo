package crash.commands.base

import org.crsh.command.CRaSHCommand
import org.crsh.cmdline.annotations.Usage
import org.crsh.cmdline.annotations.Command
import org.crsh.cmdline.annotations.Argument

import org.crsh.cmdline.annotations.Required
import org.crsh.shell.ui.UIBuilder

import fr.dr.sandbox.controller.Customer;
import fr.dr.sandbox.controller.CustomerComponent;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Spring commands to list Customer in cache for the demo application.
 * @author drieu
 *
 */
@Usage("Spring cache commands")
@Command
class spring_cache extends CRaSHCommand {

  @Usage("Show values that are loaded in cache")
  @Command
  public void showCache() {
	  def bean = context.attributes.beans["customerComponent"];
	  if (null != bean) {
		  out.println("Cache contents :");
		  List<Customer> lst = bean.getCache();
		  if (null != lst) {
			  for(Customer c : lst) {
				  out.println("Name:" + c.getName());
				  out.println("Id:" + c.getId());
				  c.show();
			  }
		  }
	  }
  }
}
