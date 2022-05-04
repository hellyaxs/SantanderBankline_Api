package com.dio.santander.Bankline.api.DTO;

import com.dio.santander.Bankline.api.Entities.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO {

    private String descricao;

    private TipoMovimentacao tipo;

    private Double valor;

    private Long idConta;
}
