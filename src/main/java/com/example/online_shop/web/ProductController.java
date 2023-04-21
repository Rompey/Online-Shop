package com.example.online_shop.web;

import com.example.online_shop.model.dto.ProductDTO;
import com.example.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDTO> getProducts(Pageable pageable) {
            return productService.getProducts(pageable);
    }

    @GetMapping("/products/pn/{productName}")
    public ProductDTO getProductByName(@PathVariable("productName") String productName) {
        return productService.getProductByName(productName);
    }

    @PostMapping("/products/add")
    public ProductDTO addNewProduct(@RequestBody ProductDTO productDTO) {
        return productService.addNewProduct(productDTO);
    }
}
