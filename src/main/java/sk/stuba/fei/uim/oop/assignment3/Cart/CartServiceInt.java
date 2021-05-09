package sk.stuba.fei.uim.oop.assignment3.Cart;

public interface CartServiceInt {
    //List<Cart> getProducts();
    Cart createCart();
    CartResponse getCart(int id);
    void deleteCart(int id);
}
