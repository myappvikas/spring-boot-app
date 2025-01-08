package com.myapp.retail.service;

import com.myapp.retail.controller.RetailController;
import com.myapp.retail.entity.Product;
import com.myapp.retail.repository.RetailServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RetailServiceImpl implements RetailService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RetailController.class);

    @Autowired
    private RetailServiceRepository retailServiceRepository;
    @Override
    public Product saveProductInformation(Product product) {
        LOGGER.info("calling RetailServiceImpl!!!!!!!!!!!!!!!!!!!!!!");
        return retailServiceRepository.save(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return retailServiceRepository.findById(productId).get();
    }

    @Override
    public Iterable<Product> getAllProduct() {
        return retailServiceRepository.findAll();
    }

    @Override
    public Product updateProductPrise(Long productId, Double newProductPrise) {
        Product existingProduct = retailServiceRepository.findById(productId).get();
        existingProduct.setProductPrise(newProductPrise);
        return retailServiceRepository.save(existingProduct);
    }

    @Override
    public void deleteProductInfo(Long productId) {
        retailServiceRepository.deleteById(productId);
    }
}
