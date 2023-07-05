package com.hillel.reziapov.homework.javapro_spring_glovo.converter;

import com.hillel.reziapov.homework.javapro_spring_glovo.dto.OrderDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Order;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderConverterTest {

    @Mock
    private Order order;

    @Test
    public void testToOrderDto() {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1", 10.0, 11));
        products.add(new Product(2, "Product 2", 20.0, 12));

        when(order.getId()).thenReturn(1);
        when(order.getDate()).thenReturn(Date.valueOf("2023-07-04"));

        OrderDto orderDto = OrderConverter.toOrderDto(order, products);

        assertEquals(1,orderDto.getId());
        assertEquals("2023-07-04", orderDto.getDate().toString());
        assertEquals(30.0, orderDto.getTotalCoast(), 0.0001);
        assertEquals(2, orderDto.getProducts().size());
    }
}
