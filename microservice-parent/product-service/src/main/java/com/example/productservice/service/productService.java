package com.example.productservice.service;

import com.example.productservice.dto.productRequest;
import com.example.productservice.dto.productResponse;

import java.util.List;

public interface productService {
   productResponse createProduct(productRequest productRequest);
   List<productResponse> getAllProducts();
  String updateProduct(String productId,productRequest productRequest);
  void deleteProduct(String productId);
}
