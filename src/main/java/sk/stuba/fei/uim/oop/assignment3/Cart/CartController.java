package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AlreadyPayedException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.AmountLimitException;
import sk.stuba.fei.uim.oop.assignment3.Product.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class CartController {

    @Autowired
    private CartServiceInt service;


    @PostMapping("/cart")
    public ResponseEntity<CartResponse> createCart(){
        CartResponse createdCart = new CartResponse();
        createdCart.setId(this.service.createCart().getId());
        return new ResponseEntity<>(createdCart,HttpStatus.CREATED);
    }
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    public ResponseEntity<CartResponse> getCart(@PathVariable int id){
        try{
            CartResponse cartR=this.service.getCart(id);
            return new ResponseEntity<>(cartR,HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CartResponse> delCart(@PathVariable int id){
        try{
            this.service.deleteCart(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/cart/{id}/add", method = RequestMethod.POST)
    public ResponseEntity<CartResponse> addToCart(@PathVariable int id,@RequestBody AddRequest add){
        try{
            CartResponse cart =this.service.addToCart(id,add.getProductId(),add.getAmount());
            return new ResponseEntity<>(cart,HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (AlreadyPayedException | AmountLimitException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/cart/{id}/pay", method = RequestMethod.GET)
    public ResponseEntity<Double> payCart(@PathVariable int id){
        try{
            double price=0;
            price=this.service.payCart(id);
            return new ResponseEntity<>(price,HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (AlreadyPayedException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
