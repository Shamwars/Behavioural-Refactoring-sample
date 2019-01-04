package ie.cct.designpatterns;
/**
 * This class describes a Product in our system. You do not need to 
 * modify this code, only use it.
 * 
 * @author Jenny
 *
 */
public final class Product {

  public final String name;
  public final double price;

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }
  
  @Override
  public boolean equals(Object other) {
    if(this == other) return true;
    if(!(other instanceof Product)) return false;
    final Product that = (Product) other;
    return this.name.equals(that.name) && this.price == that.price;
  }
  
  @Override
  public int hashCode() {
    long p = Double.doubleToLongBits(price);
    int result = 17;
    result = 31 * result + (int) (p ^ (p >>> 32));
    result = 31 * result + name.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return name + " (" + price + ")";
  }
}
