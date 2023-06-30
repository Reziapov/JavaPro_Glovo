package com.hillel.reziapov.homework.javapro_spring_glovo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {

    @Id
    private Integer id;
    private String name;
    private Double cost;
    private Integer orderId;

}
