package com.dio.santander.Bankline.api.cnab.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


@Data
@NoArgsConstructor
public class Transation {
    private Long id;
    private Integer type;
    private Date date;
    private BigDecimal value;
    private Long cpf;
    private String card;
    private Time hour;
    private String storeOwner;
    private String nomeLoja;
}
