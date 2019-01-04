package ie.cct.designpatterns;
/**
 * The DispatcherObserver interface defines what a dispatcher observer
 * should be able to do. This is one of the Observers that you must
 * fill with content
 * 
 * @author Jenny
 *
 */
public interface DispatcherObserver extends Observer {
  /**
   * Return a Dispatcher object
   * 
   * @return
   */
  public Dispatcher getDispatcher();
}
