package ie.cct.designpatterns.s67890;

import ie.cct.designpatterns.ShoppingCartTest;
import ie.cct.designpatterns.Dispatcher;
import ie.cct.designpatterns.EmailSystem;
import ie.cct.designpatterns.OrderStatus;
import ie.cct.designpatterns.ShoppingCart;

public class MyShoppingCartTest extends ShoppingCartTest {

  @Override
  public ShoppingCart cart(OrderStatus status, Dispatcher dispatcher, EmailSystem email) {
    return new MyShoppingCart(status, dispatcher, email);
  }
}
