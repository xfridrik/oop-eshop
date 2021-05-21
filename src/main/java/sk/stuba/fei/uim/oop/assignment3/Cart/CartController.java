package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartServiceInt service;


    @PostMapping("/cart")
    public ResponseEntity<CartResponse> createCart(){
        CartResponse createdCart = this.service.createCart();
        return new ResponseEntity<>(createdCart,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    public ResponseEntity<CartResponse> getCart(@PathVariable int id){
        CartResponse cartR=this.service.getCart(id);
        return new ResponseEntity<>(cartR,HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CartResponse> delCart(@PathVariable int id){
        this.service.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{id}/add", method = RequestMethod.POST)
    public ResponseEntity<CartResponse> addToCart(@PathVariable int id,@RequestBody AddRequest add){
        CartResponse cart =this.service.addToCart(id,add.getProductId(),add.getAmount());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{id}/pay", method = RequestMethod.GET)
    public ResponseEntity<String> payCart(@PathVariable int id){
        double price=this.service.payCart(id);
        return new ResponseEntity<>(Double.toString(price),HttpStatus.OK);
    }
}
