package ie.cct.designpatterns.s2014359;

import ie.cct.designpatterns.Dispatcher;
import ie.cct.designpatterns.EmailSystem;
import ie.cct.designpatterns.OrderStatus;
import ie.cct.designpatterns.Product;

public class PayPalPayment extends MyShoppingCart {
	
	private EmailSystem email;
	private String password;

	public PayPalPayment(OrderStatus status, Dispatcher dispatcher, EmailSystem email, String pwd) {
		super();
		
		this.email = email;
		this.password = pwd;
	}
	
	public PayPalPayment(String string, String string2) {
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
		if(PAY_PAL.equals(type) && limit >= total) {
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
