package ie.cct.designpatterns;
/**
 * Interface describing what any Observer should be able to do
 * You do not need to implement this Observer directly, you should
 * be implementing it via the DispatcherObserver, EmailSystemObserver 
 * and OrderStatusObservers.
 * 
 * @author Jenny
 *
 */
public interface Observer {
/**
 * Update the specific observer. They all get passed a ShoppingCart
 * not all will necessarily need it.
 * 
 * @param cart
 */
  void update(ShoppingCart cart);
}
