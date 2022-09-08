package com.dio.santander.Bankline.api.DTO;

import com.dio.santander.Bankline.api.Entities.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO {

    @Size(max = 500)
    private String descricao;

    @NotNull
    private TipoMovimentacao tipo;

    @NotNull
    private Double valor;

    @NotBlank
    private Long idConta;
}
