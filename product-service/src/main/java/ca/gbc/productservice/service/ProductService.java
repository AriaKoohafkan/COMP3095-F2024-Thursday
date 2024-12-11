package ca.gbc.productservice.service;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    // Remove the default method and always require productId to be passed
    String updateProduct(String productId, ProductRequest productRequest);

    void deleteProduct(String productId);
}
