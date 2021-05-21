package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exceptions.AlreadyPayedException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.AmountLimitException;

public interface CartServiceInt {
    CartResponse createCart();
    CartResponse getCart(int id);
    void deleteCart(int id);
    CartResponse addToCart(int id,int productId,int amount) throws AmountLimitException, AlreadyPayedException;
    double payCart(int id) throws AlreadyPayedException;
}
