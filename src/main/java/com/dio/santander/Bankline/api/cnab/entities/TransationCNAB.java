package com.dio.santander.Bankline.api.cnab.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransationCNAB {

    private Integer type;
    private String date;
    private BigDecimal value;
    private Long cpf;
    private String card;
    private String hour;
    private String storeOwner;
    private String nomeLoja;
}
