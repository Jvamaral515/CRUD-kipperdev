package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.repositories.ProductRepository;
import com.example.crud.dto.ProductDto;
import com.example.crud.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping (value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable String id){
        ProductDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable){
        Page<ProductDto> dto = service.findAll(pageable);
       return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid ProductDto dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /*@PutMapping
    public ResponseEntity update(@RequestBody @Valid ProductDto dto){
        Product product = repository.getReferenceById(dto.id());
        product.setName(dto.name());
        product.setPrice_in_cents(dto.price_in_cents());
        return ResponseEntity.ok(product);
    }*/
}
