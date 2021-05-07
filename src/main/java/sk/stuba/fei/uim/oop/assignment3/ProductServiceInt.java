package sk.stuba.fei.uim.oop.assignment3;

import java.util.List;

public interface ProductServiceInt {
    List<Product> getProducts();
    Product addProduct(ProductRequest p);
    Product getProduct(int id);
    Product updateProduct(int id,ProductRequest updateProd);
    void delProduct(int id);
}
