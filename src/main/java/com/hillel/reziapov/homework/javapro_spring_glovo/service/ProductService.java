package com.hillel.reziapov.homework.javapro_spring_glovo.service;

import com.hillel.reziapov.homework.javapro_spring_glovo.dto.ProductDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import com.hillel.reziapov.homework.javapro_spring_glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto addToOrder(ProductDto productDto, Integer orderId) {
        Product product = Product.builder()
                .cost(productDto.getCoast())
                .name(productDto.getName())
                .orderId(orderId)
                .build();
        Product save = productRepository.save(product);
        productDto.setId(save.getId());

        return productDto;
    }
}
