package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private ProductServiceInt service;

    @GetMapping("/product")
    public List<ProductResponse> getProducts(){
        return this.service.getProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
    }
    @PostMapping("/product")
    public ProductResponse addProduct(@RequestBody ProductRequest prod){
        return new ProductResponse(this.service.addProduct(prod));
    }
}
