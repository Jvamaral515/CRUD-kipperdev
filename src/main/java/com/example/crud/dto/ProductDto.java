package com.example.crud.dto;

import com.example.crud.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDto {
        private String id;

        @NotBlank
        private String name;

        @NotNull
        private Integer price_in_cents;

        private Boolean active;

        public ProductDto(String id, String name, Integer price_in_cents, Boolean active){
                this.id = id;
                this.name = name;
                this.price_in_cents = price_in_cents;
                this.active = true;
        }


        public ProductDto(Product product){
                id = product.getId();
                name = product.getName();
                price_in_cents = product.getPrice_in_cents();
                active = true;
        }

        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public Integer getPrice_in_cents() {
                return price_in_cents;
        }

        public Boolean getActive() {
                return active;
        }
}
