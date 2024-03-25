package com.example.crud.services;

import com.example.crud.domain.product.Product;
import com.example.crud.dto.ProductDto;
import com.example.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDto findById(String id){
        Optional<Product> product = repository.findById(id);
        return new ProductDto(product.get());
    }
}
