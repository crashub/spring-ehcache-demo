package crash.commands.base

import org.crsh.command.CRaSHCommand
import org.crsh.cmdline.annotations.Usage
import org.crsh.cmdline.annotations.Command
import org.crsh.cmdline.annotations.Argument

import org.crsh.cmdline.annotations.Required
import org.crsh.shell.ui.UIBuilder

import fr.dr.sandbox.controller.Customer;
import fr.dr.sandbox.controller.CustomerService;
import java.lang.reflect.Method;
import java.util.List;

@Usage("Spring commands")
class spring_cache extends CRaSHCommand {

  @Usage("list the beans")
  @Command
  public void ls() {
	UIBuilder ui = new UIBuilder()
	ui.table() {
	  row(decoration: bold, foreground: black, background: white) {
		label("BEAN"); label("TYPE"); label("VALUE")
	  }
	  context.attributes.beans.each { key, value ->
		row() {
		  label(value: key, foreground: red); label(value?.getClass().name); label("" + value)
		}
	  }
	}
	
	context.writer.print(ui);
  }

  @Usage("Show values that are loaded in cache")
  @Command
  public void showCache() {
	  def bean = context.attributes.beans["customerService"];
	  if (null != bean) {
		  out.println("Call a static method (in log file)!");
		  bean.show();
		  out.println("Call a public method to see the cache contents :");
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

  @Usage("determines if the specified bean is a singleton or not")
  @Command
  public String singleton(@Usage("the bean name") @Argument(name = 'bean name') @Required String name) {
	return "Bean $name is ${context.attributes.factory.isSingleton(name) ? '' : 'not '}a singleton";
  }
}
