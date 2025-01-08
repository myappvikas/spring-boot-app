package com.myapp.retail.controller;

import com.myapp.retail.entity.Product;
import com.myapp.retail.service.RetailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

@RestController
@RequestMapping("/my-app/retail")
public class RetailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetailController.class);

    @Autowired
    private RetailService retailService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProductInfo(@RequestBody Product product){
        LOGGER.info("calling RetailController!!!!!!!!!!!!!!!!!!!!!!");
        Product savedProductInfo = retailService.saveProductInformation(product);
        return ResponseEntity.ok(savedProductInfo);
    }

    @GetMapping("/find/{productId}")
    public ResponseEntity<Product> productById(@PathVariable Long productId){
        Product ProductInfo = retailService.getProduct(productId);
        return ResponseEntity.ok(ProductInfo);

    }

    @GetMapping("/find/all")
    public ResponseEntity<Iterable<Product>> allProduct(){
        Iterable<Product> productList = retailService.getAllProduct();
        return ResponseEntity.ok(productList);

    }

    @PutMapping("/update/{productId}/{newProductPrise}")
    public ResponseEntity<Product> updateProductInfo(@PathVariable Long productId,
                                                     @PathVariable Double newProductPrise){
        Product updatedProductInfo = retailService.updateProductPrise(productId,newProductPrise);
        return ResponseEntity.ok(updatedProductInfo);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId){
        retailService.deleteProductInfo(productId);
        return ResponseEntity.ok(null);

    }
}
