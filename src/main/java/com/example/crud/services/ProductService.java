package com.example.crud.services;

import com.example.crud.domain.product.Product;
import com.example.crud.dto.ProductDto;
import com.example.crud.repositories.ProductRepository;
import com.example.crud.services.exceptions.DatabaseException;
import com.example.crud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable){
        Page<Product> result = repository.findAllByActiveTrue(pageable);
        return result.map(x -> new ProductDto(x));
    }

    @Transactional
    public ProductDto insert (ProductDto dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto  upddate (String id, ProductDto dto){
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDto(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recuso não encontrado");
        }
    }

    @Transactional
    public void delete(String id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recuso não encontrado");
        }
        try {
            Optional<Product> result = repository.findById(id);
            Product product = result.get();
            product.setActive(false);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException ("Falha da integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice_in_cents(dto.getPrice_in_cents());
        entity.setActive(dto.getActive());
    }
}
