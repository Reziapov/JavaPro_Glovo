package com.hillel.reziapov.homework.javapro_spring_glovo.service;

import com.hillel.reziapov.homework.javapro_spring_glovo.converter.OrderConverter;
import com.hillel.reziapov.homework.javapro_spring_glovo.dto.OrderDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Order;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import com.hillel.reziapov.homework.javapro_spring_glovo.repository.OrderRepository;
import com.hillel.reziapov.homework.javapro_spring_glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDto get(Integer id) {
        return orderRepository.findById(id)
                .map(order -> OrderConverter.toOrderDto(order, productRepository.findAllByOrderId(id)))
                .orElseThrow();
    }

    public List<OrderDto> getAll() {

        return orderRepository.findAll().stream()
                .map(order ->
                        OrderConverter.toOrderDto(order, productRepository.findAllByOrderId(order.getId())))
                .toList();
    }

    public OrderDto add(OrderDto orderDto) {
        Order order = Order.builder().date(Date.valueOf(LocalDate.now())).build();
        order = orderRepository.save(order);
        Order finalOrder = order;
        List<Product> productList = orderDto.getProducts()
                .stream()
                .map(productDto -> Product.builder()
                        .name(productDto.getName())
                        .cost(productDto.getCoast())
                        .orderId(finalOrder.getId()).build())
                .toList();
        productList = productRepository.save(productList);

        return OrderConverter.toOrderDto(finalOrder, productList);

    }


}
