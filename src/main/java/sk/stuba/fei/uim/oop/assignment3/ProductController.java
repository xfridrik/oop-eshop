package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceInt service;

    @GetMapping("/product")
    public List<ProductResponse> getProducts(){
        return this.service.getProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
    }
    @PostMapping("/product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest prod){
        return new ResponseEntity<>(new ProductResponse(this.service.addProduct(prod)), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int id){
        try{
            Product product=this.service.getProduct(id);
            return new ResponseEntity<>(new ProductResponse(product),HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @GetMapping("/product")
//    public ResponseEntity<ProductResponse> getProduct(@RequestParam(name = "id") int id){
//        return new ResponseEntity<>(new ProductResponse(this.service.getProduct(id)),HttpStatus.OK);
//    }
}
