package ie.cct.designpatterns;

import java.util.Collection;
/**
 * OrderStatus class describes what gets displayed to the screen
 * throughout the ordering process. You do not need to modify this 
 * directly but you will need to use it in other places in the code.
 * 
 * @author Jenny
 *
 */
public class OrderStatus {

  public String display(Collection<Product> order, boolean paid) {
    if(order.isEmpty()) {
      System.out.println("Your shopping cart is empty");
      return "Your shopping cart is empty";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("Your order consists of:\n");
    for(Product p : order) {
      sb.append(p.price).append('\t').append(p.name).append('\n');
    }
    sb.append("Your order has");
    if(!paid) sb.append(" not");
    sb.append(" been paid.");
    System.out.println(sb);
    return sb.toString();
  }
}
