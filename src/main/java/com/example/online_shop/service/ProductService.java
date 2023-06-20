package com.example.online_shop.service;

import com.example.online_shop.mappers.ProductMapper;
import com.example.online_shop.model.Product;
import com.example.online_shop.model.dto.ProductDTO;
import com.example.online_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDTO> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAllProductsInPageFormat(pageable);
        return ProductMapper.MAPPER.map(products);
    }

    public ProductDTO getProductByName(String productName) {
        Product product = productRepository.findProductByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.MAPPER.map(product);
    }

    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product product = productRepository.save(buildProduct(productDTO));
        return getProductDTO(product);
    }

    private ProductDTO getProductDTO(Product product) {
        return new ProductDTO(product.getProductName(),
                product.getDescription(),
                product.getManufactureDate(),
                product.getPrice());
    }

    private Product buildProduct(ProductDTO productDTO) {
        return Product.builder()
                .productName(productDTO.productName())
                .description(productDTO.description())
                .manufactureDate(Timestamp.from(Instant.now()))
                .price(productDTO.price())
                .build();
    }
}
