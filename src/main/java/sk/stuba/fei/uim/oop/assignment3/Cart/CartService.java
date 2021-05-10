package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AlreadyPayedException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AmountLimitException;
import sk.stuba.fei.uim.oop.assignment3.Product.*;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductCartRepo;
import sk.stuba.fei.uim.oop.assignment3.productInCart.ProductInCart;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService implements CartServiceInt {

    private CartRepo cartRepo;
    @Autowired
    private ProductCartRepo InCartRepo;
    @Autowired
    private ProductService productService;

    @Autowired
    public CartService(CartRepo rep){
        this.cartRepo =rep;
    }

    @Override
    public Cart createCart() {
        return cartRepo.save(new Cart());
    }

    @Override
    public CartResponse getCart(int id) {
        Cart cart = cartRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return new CartResponse(cart);
    }

    @Override
    public void deleteCart(int id) {
        cartRepo.delete(cartRepo.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public CartResponse addToCart(int id, int productId, int amount) throws AmountLimitException, AlreadyPayedException {
        Cart cart = cartRepo.findById(id).orElseThrow(NoSuchElementException::new);
        Product product = productService.getProduct(productId);

        if(product.getAmount()<amount){
            throw(new AmountLimitException());
        }
        if(cart.isPayed()){
            throw (new AlreadyPayedException());
        }

        product.setAmount(product.getAmount()-amount);

        List<ProductInCart> productsInCart=cart.getProducts();
        ProductInCart productInCart=null;
        for(ProductInCart prod:productsInCart){
            if(prod.getProduct().getId()==productId){
                productInCart=prod;
                productInCart.setAmount(productInCart.getAmount()+amount);
                break;
            }
        }

        if (productInCart==null){
            productInCart=(new ProductInCart(product,cart,amount));

            productInCart=this.InCartRepo.save(productInCart);
            cart.getProducts().add(productInCart);
            product.getProducts().add(productInCart);

            this.productService.save(product);
            this.cartRepo.save(cart);
        }
        return getCart(id);
    }

    @Override
    public double payCart(int id) throws AlreadyPayedException {
        Cart cart = cartRepo.findById(id).orElseThrow(NoSuchElementException::new);
        if(cart.isPayed()){
            throw (new AlreadyPayedException());
        }
        cart.setPayed(true);
        this.cartRepo.save(cart);

        double price=0;
        for (ProductInCart product : cart.getProducts()){
            price= (price+product.getAmount()*product.getProduct().getPrice());
        }
        return price;
    }

}
