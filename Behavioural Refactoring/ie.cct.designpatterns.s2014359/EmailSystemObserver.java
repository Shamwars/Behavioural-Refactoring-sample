/**
 * Student Name: Yassin Ting
 * Student Number: 2014359
 *
 */


package ie.cct.designpatterns.s2014359;

import java.util.ArrayList;

import ie.cct.designpatterns.Dispatcher;
import ie.cct.designpatterns.EmailSystem;
import ie.cct.designpatterns.OrderStatus;

public class EmailSystemObserver extends MyShoppingCart{

	public EmailSystemObserver(OrderStatus status, Dispatcher dispatcher, EmailSystem email) {
		super();
	}
	
	public void MyShoppingCart(OrderStatus status, Dispatcher dispatcher, EmailSystem email) {
	    this.order = new ArrayList<>();
	    this.status = status;
	    this.dispatcher = dispatcher;
	    this.email = email;
	  }
}
