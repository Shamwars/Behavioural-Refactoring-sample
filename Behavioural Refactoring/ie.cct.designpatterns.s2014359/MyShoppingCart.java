/**
 * Student Name: Yassin Ting
 * Student Number: 2014359
 *
 */

package ie.cct.designpatterns.s2014359;	// change this to your student number!!!

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

  protected static List<Product> order;
  protected OrderStatus status;
  protected Dispatcher dispatcher;
  protected EmailSystem email;
  protected boolean paid;
  
  public static void main(String[] args) {
		MyShoppingCart cart = new MyShoppingCart();
		PaypalPayment();
		DebitCardPayment();
		PaypalPayment();
		
		Product product = new Product(null, 0);
				
		order.add(product);
		
		Scanner sc = new Scanner(System.in);			
		System.out.println("Select Method of Payment: ");
		
		switch(checkout) {
	    case 1:  Cash(CashPayment);
	    break;
	    case 2:  Debit Card(DebitCardPayment);
	    break;
	    case 3:  PayPal(PayPalPayment); 
	    break;
	    default: exit();
	}
		String s = sc.nextLine();
	}
  
  private static void DebitCardPayment() {
	
}

private static void PaypalPayment() {
	
}

public MyShoppingCart() {
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
    if(DEBIT_CARD.equals(type)) {
      if(balance + limit >= total) {
        balance -= total;
        paid = true;
        email.sendEmail(order);
        dispatcher.ship(order);
        status.display(order, paid);
      } 
    } else if(CASH.equals(type)) {
      if(balance >= total) {
        balance -= total;
        paid = true;
        email.sendEmail(order);
        dispatcher.ship(order);
        status.display(order, paid);
      } 
    } else if(PAY_PAL.equals(type) && limit >= total) {
        paid = true;
        email.sendEmail(order);
        dispatcher.ship(order);
        status.display(order, paid);
      }
    return paid;
  }

  @Override
  public boolean isPaid() {
    return paid;
  }
  
}
