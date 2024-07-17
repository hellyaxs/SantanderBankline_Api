package com.dio.santander.Bankline.api;

import com.dio.santander.Bankline.api.cnab.repositories.TransationRepository;
import com.dio.santander.Bankline.api.cnab.services.TransactionReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
    }
}
