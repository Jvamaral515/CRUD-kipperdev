package com.example.crud.domain.product;

import com.example.crud.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer price_in_cents;

    public Product(ProductDto dto){
        this.name = dto.name();
        this.price_in_cents = dto.price_in_cents();
    }
}
