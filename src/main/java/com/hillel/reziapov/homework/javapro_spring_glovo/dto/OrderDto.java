package com.hillel.reziapov.homework.javapro_spring_glovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    private Date date;
    private Double totalCoast;
    private List<ProductDto> products;
}
