package com.hillel.reziapov.homework.javapro_spring_glovo.service;

import com.hillel.reziapov.homework.javapro_spring_glovo.dto.ProductDto;
import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import com.hillel.reziapov.homework.javapro_spring_glovo.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    public void addToOrderTest() {
        var orderId = 16;
        ProductDto productDto = new ProductDto();
        productDto.setName("apple");
        productDto.setCoast(15.99);

        Product product = new Product();
        product.setId(16);
        product.setName(productDto.getName());
        product.setCost(productDto.getCoast());
        product.setOrderId(orderId);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto result = productService.addToOrder(productDto, orderId);

        Assert.assertNotNull(result);
        Assert.assertEquals(product.getId(), result.getId());
        Assert.assertEquals(product.getName(), result.getName());
        Assert.assertEquals(product.getCost(), result.getCoast());
        Assert.assertEquals(Optional.of(product.getOrderId()), Optional.of(orderId));
    }
}
