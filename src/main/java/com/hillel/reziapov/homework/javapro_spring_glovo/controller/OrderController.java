package com.hillel.reziapov.homework.javapro_spring_glovo.controller;

import com.hillel.reziapov.homework.javapro_spring_glovo.dto.OrderDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.dto.ProductDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.service.OrderService;
import com.hillel.reziapov.homework.javapro_spring_glovo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Integer id) {

        return orderService.get(id);
    }

    @GetMapping
    public List<OrderDto> getAll() {

        return orderService.getAll();
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto orderDto) {

        return orderService.add(orderDto);
    }

    @PostMapping("/{orderId}/product")
    public ProductDto addProductToOrder(@PathVariable Integer orderId, @RequestBody ProductDto productDto) {

        return productService.addToOrder(productDto, orderId);
    }
}
