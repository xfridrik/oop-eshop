package sk.stuba.fei.uim.oop.assignment3.productInCart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInCartResponse {
    private int productId;
    private int amount;

    public ProductInCartResponse(ProductInCart prod) {
        this.productId=prod.getProduct().getId();
        this.amount=prod.getAmount();
    }
}
