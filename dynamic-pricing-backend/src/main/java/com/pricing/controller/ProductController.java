package com.pricing.controller; // adjust to your actual package

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.pricing.model.Product;
import com.pricing.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/products")
public class ProductController {

 @Autowired
 ProductRepository productRepository;

 @GetMapping
 public List<Product> getProducts(){
  return productRepository.findAll();
 }

 @PostMapping
 public Product addProduct(@RequestBody Product p){
  return productRepository.save(p);
 }
 @GetMapping("/{id}")
public Product getProductById(@PathVariable Long id){
    return productRepository.findById(id).orElse(null);
}

 
}