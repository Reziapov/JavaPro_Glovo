package com.hillel.reziapov.homework.javapro_spring_glovo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class Order {
    @Id
    private Integer id;
    private Date date;

}
