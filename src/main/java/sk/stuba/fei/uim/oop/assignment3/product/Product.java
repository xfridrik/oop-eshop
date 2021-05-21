package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductInCart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private long amount;
    private String unit;
    private double price;
    @ManyToMany
    private List<ProductInCart> products=new ArrayList<>();


    void setAll(String name,String description,long amount,String unit,double price){
        this.name=name;
        this.description=description;
        this.amount=amount;
        this.unit=unit;
        this.price=price;
    }

}
