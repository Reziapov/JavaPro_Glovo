package com.hillel.reziapov.homework.javapro_spring_glovo.service;

import com.hillel.reziapov.homework.javapro_spring_glovo.dto.OrderDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.dto.ProductDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Order;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import com.hillel.reziapov.homework.javapro_spring_glovo.repository.OrderRepository;
import com.hillel.reziapov.homework.javapro_spring_glovo.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    public void getTest() {
        var orderId = 11;

        List<Product> products = List.of(
                Product.builder().cost(10.0).name("burger").orderId(orderId).build(),
                Product.builder().cost(15.0).name("big burger").orderId(orderId).build(),
                Product.builder().cost(5.0).name("cola").orderId(orderId).build()
        );
        Order order = Order.builder().id(orderId).date(Date.valueOf(LocalDate.now())).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(productRepository.findAllByOrderId(anyInt())).thenReturn(products);


        OrderDto actualOrderDto = orderService.get(orderId);

        Assert.assertEquals(Optional.of(30.0), actualOrderDto.getTotalCoast().describeConstable());
    }

    @Test
    public void getAllTest() {
        List<Product> products = List.of(
                Product.builder().cost(10.0).name("burger").orderId(10).build(),
                Product.builder().cost(15.0).name("big burger").orderId(10).build(),
                Product.builder().cost(5.0).name("cola").orderId(10).build()
        );
        List<Product> products1 = List.of(
                Product.builder().cost(20.0).name("mak menu").orderId(12).build(),
                Product.builder().cost(30.0).name("happy mil").orderId(12).build()
        );

        when(orderRepository.findAll()).thenReturn(List.of(
                Order.builder().id(10).date(Date.valueOf(LocalDate.now())).build(),
                Order.builder().id(12).date(Date.valueOf(LocalDate.now())).build(),
                Order.builder().id(13).date(Date.valueOf(LocalDate.now())).build()
        ));
        when(productRepository.findAllByOrderId(10)).thenReturn(products);
        when(productRepository.findAllByOrderId(12)).thenReturn(products1);

        List<OrderDto> all = orderService.getAll();
        verify(productRepository, times(1)).findAllByOrderId(10);
        verify(productRepository, times(1)).findAllByOrderId(12);


    }

    @Test
    public void addTest() {
        var orderId = 10;
        OrderDto orderDto = OrderDto.builder().id(orderId).date(Date.valueOf(LocalDate.now())).build();

        List<ProductDto> productDtos = List.of(
                ProductDto.builder().id(10).coast(10.0).name("burger").build(),
                ProductDto.builder().id(11).coast(1.99).name("ice cream").build(),
                ProductDto.builder().id(12).coast(1.00).name("coke-cola").build()
        );
        orderDto.setProducts(productDtos);

        Order savedOrder = new Order(orderId, Date.valueOf(LocalDate.now()));
        List<Product> products = List.of(
                Product.builder().cost(10.0).name("burger").orderId(10).build(),
                Product.builder().cost(15.0).name("burger menu").orderId(10).build(),
                Product.builder().cost(5.00).name("cola").orderId(10).build()
        );

        when(orderRepository.save(any((Order.class)))).thenReturn(savedOrder);
        when(productRepository.saveAll(anyList())).thenReturn(products);

        OrderDto actualOrderDto = orderService.add(orderDto);

        Assert.assertEquals(orderDto.getDate(), actualOrderDto.getDate());
        Assert.assertEquals(orderDto.getProducts().size(), actualOrderDto.getProducts().size());

        verify(orderRepository,times(1)).save(any(Order.class));
        verify(productRepository,times(1)).saveAll(anyList());
    }

}
