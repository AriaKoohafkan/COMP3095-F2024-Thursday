package com.example.productservice.service;

import com.example.productservice.dto.productRequest;
import com.example.productservice.dto.productResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class productServiceImpl implements productService {
    @Override
    public productResponse createProduct(productRequest productRequest) {
        return null;
    }

    @Override
    public List<productResponse> getAllProducts() {
        return List.of();
    }

    @Override
    public String updateProduct(String productId, productRequest productRequest) {
        return "";
    }

    @Override
    public void deleteProduct(String productId) {

    }
}
