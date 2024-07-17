package com.dio.santander.Bankline.api.DTO;

import com.dio.santander.Bankline.api.Entities.TipoMovimentacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
