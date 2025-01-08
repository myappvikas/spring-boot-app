package com.myapp.retail.service;

import com.myapp.retail.entity.Product;

import java.util.List;

public interface RetailService {

    Product saveProductInformation(Product product);

    Product getProduct(Long productId);

    Iterable<Product> getAllProduct();

    Product updateProductPrise(Long productId, Double newProductPrise);

    void deleteProductInfo(Long productId);
}
