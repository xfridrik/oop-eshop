package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class ProductResponse {
    private final int id;
    private final String name;
    private final String description;
    private final long amount;
    private final String unit;
    private final double price;
    public ProductResponse(Product product){
        this.id=product.getId();
        this.name=product.getName();
        this.description=product.getDescription();
        this.amount=product.getAmount();
        this.unit=product.getUnit();
        this.price=product.getPrice();
    }
}
