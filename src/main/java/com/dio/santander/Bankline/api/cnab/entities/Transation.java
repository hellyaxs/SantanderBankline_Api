package com.dio.santander.Bankline.api.cnab.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Transation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer type;
    private Date date;

    @Column(name = "valor")
    private BigDecimal value;

    private Long cpf;
    private String card;
    private LocalTime time;
    private String storeOwner;
    private String nomeLoja;

}
