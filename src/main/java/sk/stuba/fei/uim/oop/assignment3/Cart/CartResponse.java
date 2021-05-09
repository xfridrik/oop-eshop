package sk.stuba.fei.uim.oop.assignment3.Cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.Product.Product;

import java.util.ArrayList;

@Setter
@Getter
public class CartResponse {
    private int id;
    private ArrayList<Product> shoppingList=new ArrayList<>();
    private boolean payed;
}
