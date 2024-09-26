package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
   ProductResponse createProduct(ProductRequest productRequest);
   List<ProductResponse> getAllProducts();
  String updateProduct(String productId, ProductRequest productRequest);
  void deleteProduct(String productId);
}
