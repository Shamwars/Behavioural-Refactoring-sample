package ie.cct.designpatterns;

import java.util.Collection;

public interface ShoppingCart {
  
  public static final String CASH = "Cash";
  public static final String DEBIT_CARD = "Debit";
  public static final String PAY_PAL = "PayPal";

  public void add(Product build);

  public int numProducts();

  public double getTotal();

  public boolean remove(Product fez);

  public Collection<Product> listProducts();

  public boolean checkout(String paymentMethod, double balance, double limit);

  public boolean isPaid();

  }
