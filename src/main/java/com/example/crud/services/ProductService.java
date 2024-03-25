package com.example.crud.services;

import com.example.crud.domain.product.Product;
import com.example.crud.dto.ProductDto;
import com.example.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductDto(x));
    }

    @Transactional
    public ProductDto insert (ProductDto dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    private void copyDtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice_in_cents(dto.getPrice_in_cents());
    }
}
