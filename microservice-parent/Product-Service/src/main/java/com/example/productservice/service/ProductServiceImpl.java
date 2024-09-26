package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

private final ProductRepository productRepository;
private final MongoTemplate mongoTemplate;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

       log.debug("Creating a new product {}", productRequest.name());

        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();


        productRepository.save(product);
        log.info("product {} is saved", product.toString());
        return new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice());
    }


    @Override
    public List<ProductResponse> getAllProducts() {
        log.debug("Returning list products");
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapProductById ).toList();
    }
    private ProductResponse mapProductById(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Override
    public String updateProduct(String id, ProductRequest productRequest) {

        log.debug("Updating product {}", id);
        
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Product product=mongoTemplate.findOne(query, Product.class);

        if (product!= null) {
            product.setDescription(productRequest.description());
            product.setPrice(productRequest.price());
            product.setName(productRequest.name());
            return productRepository.save(product).getId();
        }
        return id;
    }

    @Override
    public void deleteProduct(String id) {
    log.debug("Deleting product {}", id);
    productRepository.deleteById(id);
    }
}
