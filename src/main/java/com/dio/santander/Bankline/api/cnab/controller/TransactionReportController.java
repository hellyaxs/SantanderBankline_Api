package com.dio.santander.Bankline.api.cnab.controller;

import com.dio.santander.Bankline.api.cnab.entities.Transation;
import com.dio.santander.Bankline.api.cnab.entities.TransationReport;
import com.dio.santander.Bankline.api.cnab.services.TransactionReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("transacoes")
public class TransactionReportController {

    private final TransactionReportService transactionReportService;

    public TransactionReportController(TransactionReportService transactionReportService) {
        this.transactionReportService = transactionReportService;
    }

    @GetMapping
    public ResponseEntity<List<TransationReport>> findAllByOrderByNomeLojaAscIdDesc() {
        return ResponseEntity.ok(transactionReportService.findAllByOrderByNomeLojaAscIdDesc());
    }
}
