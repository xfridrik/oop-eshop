package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements ProductServiceInt{
    @Autowired
    ProductRepo rep;

    @Override
    public List<Product> getProducts() {
        return rep.findAll();
    }
    @Override
    public Product addProduct(ProductRequest product) {
        Product addedProduct=new Product();
        addedProduct.setAll(product.getName(),product.getDescription(),product.getAmount(),product.getUnit(),product.getPrice());
        return rep.save(addedProduct);
    }

    @Override
    public Product getProduct(int id) {
        return rep.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
