package ie.cct.designpatterns;

import static ie.cct.designpatterns.ShoppingCart.CASH;
import static ie.cct.designpatterns.ShoppingCart.DEBIT_CARD;
import static ie.cct.designpatterns.ShoppingCart.PAY_PAL;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

import java.util.Collection;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

public abstract class ShoppingCartTest {
  
  private static final Product FEZ = new Product("Fez", 9.95);
  private static final Product ROOMBA = new Product("Roomba", 299.0);

  private OrderStatus status = mock(OrderStatus.class);
  private EmailSystem email = mock(EmailSystem.class);
  private Dispatcher dispatcher = mock(Dispatcher.class);
  
  public abstract ShoppingCart cart(OrderStatus status, Dispatcher dispatcher, EmailSystem emailSystem);
  
  public ShoppingCart cart() {
    return cart(status, dispatcher, email);
  }
  
  @Test
  public void canAddProductsToShoppingCart() {
    ShoppingCart cart = cart();
    assertThat(cart.numProducts(), is(0));
    cart.add(FEZ);
    assertThat(cart.numProducts(), is(1));
  }
  
  @Test
  public void canRemoveProductsFromShoppingCart() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    assertThat(cart.numProducts(), is(1));
    assertThat(cart.remove(FEZ), is(true));
    assertThat(cart.numProducts(), is(0));
  }
  
  @Test
  public void cannotRemoveProductsNotInCart() {
    ShoppingCart cart = cart();
    assertThat(cart.remove(FEZ), is(false));
  }
  
  @Test
  public void canListProductsInShoppingCart() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.numProducts(), is(2));
    assertThat(cart.listProducts(), hasItems(FEZ, ROOMBA));
  }
  
  @Test
  public void emptyCartHasTotalZero() {
    assertThat(cart().getTotal(), is(0d));
  }
  
  @Test
  public void canShowTotalPrice() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.getTotal(), is(FEZ.price + ROOMBA.price));
  }
  
  @Test
  public void canPayWithCash() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(CASH, cart.getTotal(), -500), is(true));
  }
  
  @Test
  public void cannotPayWithCashWithInsufficientBalance() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(CASH, cart.getTotal()-10, 500), is(false));
  }
  
  @Test
  public void canPayWithDebitCard() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(DEBIT_CARD, cart.getTotal(), 0d), is(true));
  }
  
  @Test
  public void cannotPayWithDebitCardWithInsufficientBalanceAndNoLimit() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(DEBIT_CARD, cart.getTotal()*.99, 0d), is(false));
  }
  
  @Test
  public void canPayWithDebitCardWithInsufficientBalanceAndSufficientLimit() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(DEBIT_CARD, cart.getTotal()-100, 100d), is(true));
  }
  
  @Test
  public void canPayWithPayPalWithSufficientLimit() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(PAY_PAL, -500, cart.getTotal()), is(true));
  }
  
  @Test
  public void cannotPayWithPayPalWithInsufficientLimit() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    assertThat(cart.checkout(PAY_PAL, 500, cart.getTotal()-10), is(false));
  }
  
  @Test
  public void willDisplayOrderStatusWhenProductAdded() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    verify(status).display((Collection<Product>) argThat(hasItems(FEZ)), eq(false));
  }
  
  @Test
  public void willDisplayOrderStatusWhenProductRemoved() {
    ShoppingCart cart = cart();
    cart.remove(FEZ);
    verify(status).display(argThat(is(emptyCollectionOf(Product.class))), eq(false));
  }
  
  @Test
  public void willDisplayOrderStatusWhenOrderPaid() {
    ShoppingCart cart = cart();
    cart.checkout(CASH, cart.getTotal(), 0);
    verify(status).display(argThat(is(emptyCollectionOf(Product.class))), eq(true));
  }
  
  @Test
  public void willEmailInvoiceWhenOrderPaid() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    cart.checkout(CASH, cart.getTotal(), 0);
    verify(email).sendEmail((Collection<Product>) argThat(hasItems(FEZ, ROOMBA)));
  }
  
  @Test
  public void willNotEmailInvoiceWhenProductAdded() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    verify(email, never()).sendEmail(ArgumentMatchers.<Collection<Product>>any());
  }
  
  @Test
  public void willNotEmailInvoiceWhenProductRemoved() {
    ShoppingCart cart = cart();
    cart.remove(FEZ);
    verify(email, never()).sendEmail(ArgumentMatchers.<Collection<Product>>any());
  }
  
  @Test
  public void willDispatchOrderWhenOrderPaid() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    cart.add(ROOMBA);
    cart.checkout(CASH, cart.getTotal(), 0);
    verify(dispatcher).ship((Collection<Product>) argThat(hasItems(FEZ, ROOMBA)));
  }
  
  @Test
  public void willNotDispatchOrderWhenProductAdded() {
    ShoppingCart cart = cart();
    cart.add(FEZ);
    verify(dispatcher, never()).ship(ArgumentMatchers.<Collection<Product>>any());
  }
  
  @Test
  public void willNotDispatchOrderWhenProductRemoved() {
    ShoppingCart cart = cart();
    cart.remove(FEZ);
    verify(dispatcher, never()).ship(ArgumentMatchers.<Collection<Product>>any());
  }
}
