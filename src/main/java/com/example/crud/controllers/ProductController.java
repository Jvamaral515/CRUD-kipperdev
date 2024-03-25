package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.repositories.ProductRepository;
import com.example.crud.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity findAll(){
        var allProducts = repository.findAll();
       return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid ProductDto dto){
        Product p = new Product(dto);
        System.out.println(dto);
        repository.save(p);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDto dto){
        Product product = repository.getReferenceById(dto.id());
        product.setName(dto.name());
        product.setPrice_in_cents(dto.price_in_cents());
        return ResponseEntity.ok(product);
    }
}
