package sk.stuba.fei.uim.oop.assignment3.productInCart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.Cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.Product.Product;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;

    private int amount;

    public ProductInCart(Product product, Cart cart, int amount) {
        this.cart=cart;
        this.product=product;
        this.amount=amount;
    }

    public ProductInCart() {
    }
}
