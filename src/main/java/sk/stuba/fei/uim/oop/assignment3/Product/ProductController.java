package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Product product=this.service.getProduct(id);
        return new ResponseEntity<>(new ProductResponse(product),HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProductResponse> delProduct(@PathVariable int id){
        this.service.delProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id,@RequestBody ProductRequest updateProd){
        Product product=this.service.updateProduct(id,updateProd);
        return new ResponseEntity<>(new ProductResponse(product),HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}/amount", method = RequestMethod.GET)
    public ResponseEntity<AmountResponse> getAmount(@PathVariable int id){
        AmountResponse productAmount=this.service.getAmount(id);
        return new ResponseEntity<>(productAmount,HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}/amount", method = RequestMethod.POST)
    public ResponseEntity<AmountResponse> getAmount(@PathVariable int id,@RequestBody ProductRequest amountProd){
        AmountResponse productAmount=this.service.addAmount(id,amountProd);
        return new ResponseEntity<>(productAmount,HttpStatus.OK);
    }
}
