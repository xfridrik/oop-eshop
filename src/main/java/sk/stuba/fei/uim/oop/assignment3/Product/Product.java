package sk.stuba.fei.uim.oop.assignment3.Product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    void setAll(String name,String description,int amount,String unit,double price){
        this.name=name;
        this.description=description;
        this.amount=amount;
        this.unit=unit;
        this.price=price;
    }

}
