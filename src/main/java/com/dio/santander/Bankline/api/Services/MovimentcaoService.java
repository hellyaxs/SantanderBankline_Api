package com.dio.santander.Bankline.api.Services;

import com.dio.santander.Bankline.api.DTO.MovimentacaoDTO;
import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Entities.Movimentacao;
import com.dio.santander.Bankline.api.Entities.TipoMovimentacao;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import com.dio.santander.Bankline.api.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentcaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CorretistaRepository corretistaRepository;

    public void save(MovimentacaoDTO novaMovimentacao){
        Movimentacao movimentacao = new Movimentacao();

        Double valor = novaMovimentacao.getTipo() == TipoMovimentacao.RECEITA ? novaMovimentacao.getValor() : novaMovimentacao.getValor() * -1 ;

        movimentacao.setDate(LocalDateTime.now());
        movimentacao.setDescricao(novaMovimentacao.getDescricao());
        movimentacao.setIdConta(novaMovimentacao.getIdConta());
        movimentacao.setTipo(novaMovimentacao.getTipo());
        movimentacao.setValor(valor);

        corretistaRepository.findById(novaMovimentacao.getIdConta())
                .ifPresent(corretista -> {
                    corretista
                            .getConta()
                            .setSaldo(corretista.getConta().getSaldo() + valor);
                corretistaRepository
                            .save(corretista);
                });

        repository.save(movimentacao);

}
}
