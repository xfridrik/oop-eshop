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
    private int cartId;
    private int productId;
//    @OneToMany
//    private Cart cart;
//    @OneToMany
//    private Product product;
    private int amount;

    public ProductInCart(int cartId, int productId, int amount) {
        this.cartId=cartId;
        this.productId=productId;
        this.amount=amount;
    }

    public ProductInCart() {
    }
}
