package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
