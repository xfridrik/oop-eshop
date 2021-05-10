package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AlreadyPayedException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AmountLimitException;
import sk.stuba.fei.uim.oop.assignment3.Product.*;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductCartRepo;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductInCartResponse;

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
        List<ProductInCartResponse> products =new ArrayList<>();
        List<ProductInCart> allProducts = new ArrayList<>(InCartRepo.findAll());
        for(ProductInCart product:allProducts){
            if(product.getCartId()==id){
                products.add(new ProductInCartResponse(product));
                //products.add(productRepo.findById(product.getProductId()).orElseThrow(NoSuchElementException::new));
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

    @Override
    public CartResponse addToCart(int id, int productId, int amount) throws AmountLimitException, AlreadyPayedException {
        Cart cart = rep.findById(id).orElseThrow(NoSuchElementException::new);
        Product product = productRepo.findById(productId).orElseThrow(NoSuchElementException::new);
        if(product.getAmount()<amount){
            throw(new AmountLimitException());
        }
        if(cart.isPayed()){
            throw (new AlreadyPayedException());
        }
        product.setAmount(product.getAmount()-amount);
        InCartRepo.save(new ProductInCart(id,productId,amount));
        return getCart(id);
    }

    @Override
    public double payCart(int id) throws AlreadyPayedException {
        Cart cart = rep.findById(id).orElseThrow(NoSuchElementException::new);
        if(cart.isPayed()){
            throw (new AlreadyPayedException());
        }
        else{
            cart.setPayed(true);
        }
        double price=0;
        List<ProductInCart> allProducts = new ArrayList<>(InCartRepo.findAll());
        for(ProductInCart product:allProducts){
            if(product.getCartId()==id){
                Product prod=productRepo.findById(product.getProductId()).orElseThrow(NoSuchElementException::new);
                price=price+product.getAmount()*prod.getPrice();
            }
        }
        return price;
    }

}
