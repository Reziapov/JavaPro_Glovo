package com.hillel.reziapov.homework.javapro_spring_glovo.converter;

import com.hillel.reziapov.homework.javapro_spring_glovo.dto.OrderDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.dto.ProductDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Order;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import java.util.List;

public class OrderConverter {
    public static OrderDto toOrderDto(Order order, List<Product> products) {
        List<ProductDto> productDtos = products.stream()
                .map(p -> ProductDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .coast(p.getCost())
                        .build())
                .toList();
        double sum = productDtos.stream().mapToDouble(ProductDto::getCoast).sum();
        return OrderDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .totalCoast(sum)
                .products(productDtos)
                .build();
    }
}
