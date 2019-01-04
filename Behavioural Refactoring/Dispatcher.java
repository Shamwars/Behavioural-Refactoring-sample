package ie.cct.designpatterns;

import java.util.Collection;
/**
 * The Dispatcher class is used to dispatch the items we 
 * have in the shopping cart once paid for. You do not need to 
 * modify anything here, but you will need to use this class
 * elsewhere.
 * 
 * @author Jenny
 *
 */
public class Dispatcher {

  public String ship(Collection<Product> order) {
    StringBuilder sb = new StringBuilder();
    sb.append("The following items have been shipped\n");
    for(Product p : order) {
      sb.append(p.name).append('\n');
    }
    System.out.println(sb);
    return sb.toString();
  }
}
