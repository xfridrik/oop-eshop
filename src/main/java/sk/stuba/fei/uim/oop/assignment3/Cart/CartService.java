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
    //@Autowired
    private CartRepo rep;
    @Autowired
    private ProductCartRepo InCartRepo;
    @Autowired
    private ProductService productService;

    @Autowired
    public CartService(CartRepo rep){
        this.rep=rep;
    }

    @Override
    public Cart createCart() {
        return rep.save(new Cart());
    }

    @Override
    public CartResponse getCart(int id) {
        Cart cart = rep.findById(id).orElseThrow(NoSuchElementException::new);
        return new CartResponse(cart);
    }

    @Override
    public void deleteCart(int id) {
        rep.delete(rep.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public CartResponse addToCart(int id, int productId, int amount) throws AmountLimitException, AlreadyPayedException {
        Cart cart = rep.findById(id).orElseThrow(NoSuchElementException::new);
        Product product = productService.getProduct(productId);

        if(product.getAmount()<amount){
            throw(new AmountLimitException());
        }
        if(cart.isPayed()){
            throw (new AlreadyPayedException());
        }
        product.setAmount(product.getAmount()-amount);

        ProductInCart prodInC=(new ProductInCart(product,cart,amount));

        prodInC=this.InCartRepo.save(prodInC);

        cart.getProducts().add(prodInC);
        product.getProducts().add(prodInC);

        this.productService.save(product);
        this.rep.save(cart);

        return getCart(id);
    }

    @Override
    public double payCart(int id) throws AlreadyPayedException {
        Cart cart = rep.findById(id).orElseThrow(NoSuchElementException::new);
        //System.out.println(cart.isPayed());
        if(cart.isPayed()){
            throw (new AlreadyPayedException());
        }
        //System.out.println(cart.isPayed());
        cart.setPayed(true);
        //System.out.println(cart.isPayed());
        this.rep.save(cart);

        double price=0;
        for (ProductInCart product : cart.getProducts()){
            price=price+product.getAmount()*product.getProduct().getPrice();
        }
        return price;
    }

}
