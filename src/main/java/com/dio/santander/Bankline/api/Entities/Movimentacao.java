package com.dio.santander.Bankline.api.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="tab_movimentacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private LocalDateTime date;
    @Column
    private String descricao;

    @Column
    private Double valor;

    @Column
    private Long IdConta;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

}
