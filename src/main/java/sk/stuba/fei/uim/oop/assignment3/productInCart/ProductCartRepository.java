package sk.stuba.fei.uim.oop.assignment3.productInCart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends CrudRepository<ProductInCart, Integer> {
    List<ProductInCart> findAll();
}
