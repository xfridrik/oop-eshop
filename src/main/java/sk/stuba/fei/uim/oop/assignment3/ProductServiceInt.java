package sk.stuba.fei.uim.oop.assignment3;

import java.util.List;

public interface ProductServiceInt {
    List<Product> getProducts();
    Product addProduct(ProductRequest p);
}
