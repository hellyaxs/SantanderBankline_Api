package com.dio.santander.Bankline.api.Services;

import com.dio.santander.Bankline.api.DTO.MovimentacaoDTO;
import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Entities.Movimentacao;
import com.dio.santander.Bankline.api.Entities.TipoMovimentacao;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import com.dio.santander.Bankline.api.repository.MovimentacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

@Service
public class MovimentcaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CorretistaRepository corretistaRepository;

    public void save(MovimentacaoDTO novaMovimentacao){
        var movimentacao = new Movimentacao();

        Double valor = novaMovimentacao.getTipo() == TipoMovimentacao.RECEITA ? novaMovimentacao.getValor() : novaMovimentacao.getValor() * -1 ;
        novaMovimentacao.setValor(valor);
        BeanUtils.copyProperties(novaMovimentacao,movimentacao);
        movimentacao.setDate(LocalDateTime.now(ZoneId.of("UTC")));


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
    public List<Movimentacao> findByIdConta(Long IdConta){
        Iterable<Long> test = Collections.singleton(IdConta);
        return repository.findByIdConta(IdConta);
    }
}
