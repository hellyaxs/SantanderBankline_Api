package com.dio.santander.Bankline.api.cnab.entities;

import java.math.BigDecimal;
import java.util.List;

public record TransationReport(
        String nomeLoja,
        BigDecimal valorTotal,
        List<Transation> transations
) {
    public TransationReport addTotal(BigDecimal valor) {
        return new TransationReport(nomeLoja, valorTotal.add(valor), transations);

    }

    public TransationReport addTransacao(Transation trasacao) {
        transations.add(trasacao);
        return new TransationReport(nomeLoja, valorTotal, transations);
    }
}