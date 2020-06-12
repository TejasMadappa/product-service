package com.tejas.springcloud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tejas.springcloud.dto.Coupon;
import com.tejas.springcloud.exceptions.ApiRequestException;
import com.tejas.springcloud.model.Product;
import com.tejas.springcloud.repos.ProductRepo;

@RestController
public class ProductRestController {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {

		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);

		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));

		return productRepo.save(product);
	}

	@RequestMapping(value = "/products", method = RequestMethod.PUT)
	public Product updateProduct(@RequestParam Long id, @RequestParam String name, @RequestParam String description)
			throws Exception {
		Optional<Product> optional = productRepo.findById(id);

		if (optional.isPresent()) {
			Product existingProduct = optional.get();
			existingProduct.setDescription(description);
			existingProduct.setName(name);

			return productRepo.save(existingProduct);
		}

		throw new Exception();
	}

	@RequestMapping(value = "/products", method = RequestMethod.PATCH)
	public Product updateProductUsingPatch(@RequestParam Long id, @RequestParam String name,
			@RequestParam String description) throws Exception {
		Optional<Product> optional = productRepo.findById(id);

		if (optional.isPresent()) {
			Product existingProduct = optional.get();
			existingProduct.setDescription(description);
			existingProduct.setName(name);

			return productRepo.save(existingProduct);
		}

		throw new Exception();
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@GetMapping(value = "/products/{id}")
	public Product getProductById(@PathVariable Long id) {
		Optional<Product> optional = productRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new ApiRequestException("Oops the resource you are looking cannot be found");
	}
}
