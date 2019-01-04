package ie.cct.designpatterns;

import java.util.Collection;
/**
 * This class is used to send an email once payment has been made
 * on the items in the shopping basket. You do not need to modify
 * anything in here but you will need to use it in other places.
 * 
 * @author Jenny
 *
 */
public class EmailSystem {

  public String sendEmail(Collection<Product> order) {
    StringBuilder sb = new StringBuilder();
    sb.append("Thank you for shopping with us.\n Your order details are as follows:\n");
    for(Product p : order) {
      sb.append(p.price).append("\t").append(p.name).append('\n');
    }
    sb.append("-------").append("-------");
    System.out.println(sb);
    return sb.toString();
  }
}
