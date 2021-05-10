package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends CrudRepository<Cart, Integer> {
    //List<Cart> findAll();
}
