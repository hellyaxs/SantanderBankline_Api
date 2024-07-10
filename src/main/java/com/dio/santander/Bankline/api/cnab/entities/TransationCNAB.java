package com.dio.santander.Bankline.api.cnab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransationCNAB {

    private Integer tipo;
    private String data;
    private BigDecimal valor;
    private Long cpf;
    private String cartao;
    private String hora;
    private String DonoLoja;
    private String nomeLoja;
}
