package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Product.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService implements CartServiceInt {
    @Autowired
    CartRepo rep;
    @Autowired
    ProductCartRepo InCartRepo;
    @Autowired
    ProductRepo productRepo;

    @Override
    public Cart createCart() {
        return rep.save(new Cart());
    }

    @Override
    public CartResponse getCart(int id) {
        Cart cart = rep.findById(id).orElseThrow(NoSuchElementException::new);
        CartResponse response = new CartResponse(cart);
        ArrayList<Product> products =new ArrayList<>();
        List<ProductInCart> allProducts = new ArrayList<>(InCartRepo.findAll());
        for(ProductInCart product:allProducts){
            if(product.getCartId()==id){
                products.add(productRepo.findById(product.getProductId()).orElseThrow(NoSuchElementException::new));
            }
        }
        response.setShoppingList(products);
        return response;
    }

    @Override
    public void deleteCart(int id) {
        List<ProductInCart> allProducts = new ArrayList<>(InCartRepo.findAll());
        for(ProductInCart product:allProducts){
            if(product.getCartId()==id){
                InCartRepo.delete(product);
            }
        }
        rep.delete(rep.findById(id).orElseThrow(NoSuchElementException::new));
    }

}
