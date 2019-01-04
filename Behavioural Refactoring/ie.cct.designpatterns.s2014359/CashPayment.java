/**
 * Student Name: Yassin Ting
 * Student Number: 2014359
 *
 */


package ie.cct.designpatterns.s2014359;

import ie.cct.designpatterns.Dispatcher;
import ie.cct.designpatterns.EmailSystem;
import ie.cct.designpatterns.OrderStatus;
import ie.cct.designpatterns.Product;

public class CashPayment extends MyShoppingCart{

	public CashPayment(OrderStatus status, Dispatcher dispatcher, EmailSystem email) {
		super();
	}
	
	public double getTotal() {
		double total = 0;
		for(Product p : order) {
			total += p.price;
		}
		return total;
	}
	
	public boolean checkout(String type, double balance, double limit) {
		double total = 0;
	    for(Product p : order) {
	    	total += p.price;
	    }
	    if(CASH.equals(type)) {
	    	if(balance >= total) {
	    		balance -= total;
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
