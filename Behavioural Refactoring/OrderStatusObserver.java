package ie.cct.designpatterns;
/**
 * The OrderStatusObserver interface describes how the 
 * OrderStatusObserver should be implemented. This is one of the 
 * observers you need to implement (fill with content, existing and
 * some of your own).
 * 
 * @author Jenny
 *
 */
public interface OrderStatusObserver extends Observer {

  public OrderStatus getOrderStatus();
}
