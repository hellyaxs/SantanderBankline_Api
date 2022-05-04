package com.dio.santander.Bankline.api.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="tab_corretista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Corretista {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 20)
   private String cfp;

   @Column(length = 60)
   private String nome;

   @Embedded
   private Conta conta;
}
