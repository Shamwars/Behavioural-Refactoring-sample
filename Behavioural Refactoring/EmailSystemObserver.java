package ie.cct.designpatterns;
/**
 * The EmailSystemObserver interface is one of the Observer 
 * interfaces you need to implement. That is, fill it with your own
 * and existing content. 
 * 
 * @author Jenny
 *
 */
public interface EmailSystemObserver extends Observer {
/**
 * return an EmailSystem object
 * @return
 */
  public EmailSystem getEmailSystem();
}
