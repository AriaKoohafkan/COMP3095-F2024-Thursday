package com.example.productservice.model;

import lombok.*;

import java.math.BigDecimal;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class product {

    @Id
    private String id;

    private String name;
    private String description;
    private BigDecimal price;



}
