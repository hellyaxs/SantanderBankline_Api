package com.dio.santander.Bankline.api.cnab.services;

import com.dio.santander.Bankline.api.cnab.entities.Transation;
import com.dio.santander.Bankline.api.cnab.entities.TransationReport;
import com.dio.santander.Bankline.api.cnab.repositories.TransationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TransactionReportService {

    private final TransationRepository transactionRepository;

    public TransactionReportService(TransationRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<TransationReport> findAllByOrderByNomeLojaAscIdDesc() {
        var trasacoes =  transactionRepository.findAllByOrderByNomeLojaAscIdDesc();


        var reportMap = new LinkedHashMap<String,TransationReport>();

        trasacoes.forEach(trasacao ->{
            String nomeLoja = trasacao.getNomeLoja();

            reportMap.compute(nomeLoja, (key, existingReport) -> {
             var report = (existingReport != null) ?  existingReport: new TransationReport(key, BigDecimal.ZERO,new ArrayList<>());

               return report.addTotal(trasacao.getValue()).addTransacao(trasacao);
            });
        });

        return  new ArrayList<>(reportMap.values());
    }
}
