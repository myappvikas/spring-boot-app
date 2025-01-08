package com.myapp.retail;

import com.myapp.retail.entity.Product;
import com.myapp.retail.service.RetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetailApplication implements CommandLineRunner {

	@Autowired
	private RetailService retailService;

	public static void main(String[] args) {
		SpringApplication.run(RetailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product productInfo = new Product();
		productInfo.setProductName("Moto Edge");
		productInfo.setProductPrise(23000d);
		productInfo.setProductDescription("50 GB");
		productInfo.setProductCompanyName("Motorola");
		productInfo.setShopName("My Mobile Shop");
		productInfo.setShopAddress("Dullahpur");
		productInfo.setProductQuantity(10);

		Product p = retailService.saveProductInformation(productInfo);
		System.out.println(p);

	}
}
