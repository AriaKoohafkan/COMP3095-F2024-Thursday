package ca.gbc.orderservice.dto;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,              // Optional if DB auto-generates
        String orderNumber,   // Changed to camelCase
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
