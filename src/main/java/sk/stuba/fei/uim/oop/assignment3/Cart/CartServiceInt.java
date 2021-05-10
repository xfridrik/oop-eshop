package sk.stuba.fei.uim.oop.assignment3.Cart;

import sk.stuba.fei.uim.oop.assignment3.Exceptions.AlreadyPayedException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AmountLimitException;

import javax.management.InvalidAttributeValueException;

public interface CartServiceInt {
    //List<Cart> getProducts();
    Cart createCart();
    CartResponse getCart(int id);
    void deleteCart(int id);
    CartResponse addToCart(int id,int productId,int amount) throws AmountLimitException, AlreadyPayedException;
    double payCart(int id) throws AlreadyPayedException;
    //addProductToCart()
}
