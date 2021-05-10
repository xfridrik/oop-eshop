package sk.stuba.fei.uim.oop.assignment3.Cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.Product.Product;
import sk.stuba.fei.uim.oop.assignment3.Product.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductInCartResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class CartResponse {
    private int id;
    private List<ProductInCartResponse> shoppingList=new ArrayList<>();
    private boolean payed;

    public CartResponse() {
    }
    public CartResponse(Cart cart) {
        this.id=cart.getId();
        this.payed=cart.isPayed();
        this.shoppingList=cart.getProducts().stream().map(ProductInCartResponse::new).collect(Collectors.toList());
    }
}
