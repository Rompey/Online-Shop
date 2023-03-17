package com.example.online_shop.mappers;

import com.example.online_shop.model.Product;
import com.example.online_shop.model.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product map(ProductDTO productDTO);

    ProductDTO map(Product product);

    default Page<ProductDTO> map(Page<Product> products){
        return products.map(this::map);
    }
}
