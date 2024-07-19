package com.dio.santander.Bankline.api;

import com.dio.santander.Bankline.api.cnab.entities.Transation;
import com.dio.santander.Bankline.api.cnab.repositories.TransationRepository;
import com.dio.santander.Bankline.api.cnab.services.TransactionReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrasacationServiceTest {
    //Padrão AAA
    //Arrange  => referencia a preparação do cenário de teste
    //Act  -> refere-se a execução do teste
    //Assert   -> refere-se a verificação dos resultados

    @InjectMocks
    private TransactionReportService transactionService;

    @Mock
    private TransationRepository transactionRepository;



    @Test
    public void  testListAllTransactions() {
        //TODO
        final String lojaA = "Loja A", lojaB = "Loja B";


        var mockTransactions = List.of(
                new Transation(3L, 2, new Date(System.currentTimeMillis()), BigDecimal.TEN, 123456789L, "1234", LocalTime.now(), "Dono A", lojaA),
                new Transation(1L, 1, new Date(System.currentTimeMillis()), BigDecimal.TEN, 123456789L, "1234", LocalTime.now(), "Dono A", lojaB)
        );
        when(transactionRepository.findAllByOrderByNomeLojaAscIdDesc())
                .thenReturn(mockTransactions);

        var report = transactionService.findAllByOrderByNomeLojaAscIdDesc();

       assert(report.size() == 2);

       report.forEach(transation ->{
           if (transation.nomeLoja().equals(lojaA)){
               assert(BigDecimal.valueOf(10).equals(transation.valorTotal()));
           }else if (transation.nomeLoja().equals(lojaB)){
               assert(BigDecimal.valueOf(10).equals(transation.valorTotal()));
           }
       });
    }
}
