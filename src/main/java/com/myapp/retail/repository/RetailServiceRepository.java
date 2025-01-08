package com.myapp.retail.repository;

import com.myapp.retail.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailServiceRepository extends CrudRepository<Product, Long> {
}
