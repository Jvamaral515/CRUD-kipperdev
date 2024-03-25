package com.example.crud.controllers;

import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.ProductDto;
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
        System.out.println(dto);
        return ResponseEntity.ok().build();
    }
}
