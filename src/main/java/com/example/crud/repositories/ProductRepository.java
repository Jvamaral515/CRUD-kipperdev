package com.example.crud.repositories;

import com.example.crud.domain.product.Product;
import com.example.crud.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ProductRepository extends JpaRepository<Product, String> {
 
}
