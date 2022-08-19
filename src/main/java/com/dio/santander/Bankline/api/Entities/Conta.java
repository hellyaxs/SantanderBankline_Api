package com.dio.santander.Bankline.api.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    @Column(name = "conta_numero")
    private Long numero;
    @Column(name="conta_saldo")
    private Double saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

}
