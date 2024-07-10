package com.dio.santander.Bankline.api.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name ="tab_corretista")
@Data
@Builder
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

   @Column(length = 60,nullable = false)
   private String username;

   @JsonIgnore
   @Column(length = 100,nullable = false)
   private String password;

   @Embedded
   private Conta conta;

   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name ="tab_suer_roles", joinColumns = @JoinColumn(name = "username"))
   @Column(name = "role_id")
   private List<String> roles;
}
