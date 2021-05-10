package sk.stuba.fei.uim.oop.assignment3.productInCart;

public class ProductInCartResponse {
    private int productId;
    private int amount;

    public ProductInCartResponse(ProductInCart prod) {
        this.productId=prod.getProductId();
        this.amount=prod.getAmount();
    }
}
