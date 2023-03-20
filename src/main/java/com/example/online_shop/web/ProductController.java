package com.example.online_shop.web;

import com.example.online_shop.model.dto.ProductDTO;
import com.example.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getProducts(Pageable pageable) {
            return productService.getProducts(pageable);
    }

    @GetMapping("/pn/{productName}")
    public ProductDTO getProductByName(@PathVariable("productName") String productName) {
        return productService.getProductByName(productName);
    }

    @PostMapping("/add")
    public ProductDTO addNewProductIfNotExist(@RequestBody ProductDTO productDTO) {
        return productService.addNewProductIfNotExist(productDTO);
    }
}
