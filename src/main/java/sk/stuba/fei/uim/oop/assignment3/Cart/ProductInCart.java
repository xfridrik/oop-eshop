package sk.stuba.fei.uim.oop.assignment3.Cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int cartId;
    private int productId;

    public ProductInCart(int cartId, int productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    public ProductInCart() {

    }
}
