package com.hillel.reziapov.homework.javapro_spring_glovo.repository;

import com.hillel.reziapov.homework.javapro_spring_glovo.entity.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAll();
}
