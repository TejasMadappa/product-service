package com.tejas.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	

}
