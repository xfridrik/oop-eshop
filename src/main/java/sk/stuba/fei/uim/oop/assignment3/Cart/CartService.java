package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Product.*;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService implements CartServiceInt {
    @Autowired
    CartRepo rep;

    @Override
    public Cart createCart() {
        return rep.save(new Cart());
    }

}
