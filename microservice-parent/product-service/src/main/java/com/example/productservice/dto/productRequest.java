package com.example.productservice.dto;

import org.springframework.context.annotation.EnableMBeanExport;

import java.math.BigDecimal;

public record productRequest(
        String id,
        String name,
        String description,
         BigDecimal price
) { }
