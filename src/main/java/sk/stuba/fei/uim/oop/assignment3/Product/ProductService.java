package sk.stuba.fei.uim.oop.assignment3.Product;

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

    @Override
    public Product updateProduct(int id,ProductRequest updateReq) {
        Product updateProduct=getProduct(id);
        if (updateReq.getName()!=null){
            updateProduct.setName(updateReq.getName());
        }
        if (updateReq.getDescription()!=null){
            updateProduct.setDescription(updateReq.getDescription());
        }
        return updateProduct;
    }

    @Override
    public void delProduct(int id) {
        Product delProduct=getProduct(id);
        this.rep.delete(delProduct);
    }

    @Override
    public AmountResponse getAmount(int id) {
        Product amProduct=getProduct(id);
        return new AmountResponse(amProduct.getAmount());
    }

    @Override
    public AmountResponse addAmount(int id,ProductRequest amountReq) {
        Product amProduct=getProduct(id);
        amProduct.setAmount(amProduct.getAmount()+ amountReq.getAmount());
        return new AmountResponse(amProduct.getAmount());
    }


}
