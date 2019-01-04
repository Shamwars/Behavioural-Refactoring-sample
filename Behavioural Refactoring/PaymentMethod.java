package ie.cct.designpatterns;
/**
 * This interface is the main description of the various strategy
 * objects we need for the strategy pattern. You do not implement
 * this interface directly, it will be implemented via the CashPayment, 
 * DebitCardPayment, and PayPayPayment Observers.
 * 
 * @author Jenny
 *
 */
public interface PaymentMethod {
  
  public boolean pay(double amount);
  
  public double getBalance();
  
  public double getLimit();

}
