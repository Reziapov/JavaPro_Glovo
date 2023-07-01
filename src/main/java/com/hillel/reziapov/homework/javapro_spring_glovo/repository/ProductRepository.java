package com.hillel.reziapov.homework.javapro_spring_glovo.repository;

import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAllByOrderId(Integer orderId);

    List<Product> save(List<Product> products);
}
