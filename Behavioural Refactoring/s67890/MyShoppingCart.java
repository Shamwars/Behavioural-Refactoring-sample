package ie.cct.designpatterns.s67890;	// change this to your student number!!!

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ie.cct.designpatterns.Dispatcher;
import ie.cct.designpatterns.EmailSystem;
import ie.cct.designpatterns.OrderStatus;
import ie.cct.designpatterns.Product;
import ie.cct.designpatterns.ShoppingCart;
/**
 * This is the class that you will refactor. You need to pull code out 
 * from this class and put it in the correct places in the updated 
 * structure (as described in the assignment brief).
 * @author Jenny
 *
 */
public class MyShoppingCart implements ShoppingCart {

  private List<Product> order;
  private OrderStatus status;
  private Dispatcher dispatcher;
  private EmailSystem email;
  private boolean paid;
  
  public MyShoppingCart(OrderStatus status, Dispatcher dispatcher, EmailSystem email) {
    this.order = new ArrayList<>();
    this.status = status;
    this.dispatcher = dispatcher;
    this.email = email;
  }
  
  @Override
  public void add(Product product) {
    order.add(product);
    status.display(order, paid);
  }

  @Override
  public int numProducts() {
    return order.size();
  }

  @Override
  public boolean remove(Product product) {
    boolean success = order.remove(product);
    status.display(order, paid);
    return success;
  }

  @Override
  public Collection<Product> listProducts() {
    return Collections.unmodifiableCollection(order);
  }

  @Override
  public double getTotal() {
    double total = 0;
    for(Product p : order) {
      total += p.price;
    }
    return total;
  }

  @Override
  public boolean checkout(String type, double balance, double limit) {
    double total = 0;
    for(Product p : order) {
      total += p.price;
    }
    if(type.equals(DEBIT_CARD)) {
      if(balance + limit >= total) {
        balance -= total;
        paid = true;
        email.sendEmail(order);
        dispatcher.ship(order);
        status.display(order, paid);
      } 
    } else if(type.equals(CASH)) {
      if(balance >= total) {
        balance -= total;
        paid = true;
        email.sendEmail(order);
        dispatcher.ship(order);
        status.display(order, paid);
      } 
    } else if(type.equals(PAY_PAL)) {
      if(limit >= total) {
        paid = true;
        email.sendEmail(order);
        dispatcher.ship(order);
        status.display(order, paid);
      } 
    }
    return paid;
  }

  @Override
  public boolean isPaid() {
    return paid;
  }
  
}
