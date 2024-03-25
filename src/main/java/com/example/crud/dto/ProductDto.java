package com.example.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(

        String id,

        @NotBlank
        String name,

        @NotNull
        Integer price_in_cents) {
}
