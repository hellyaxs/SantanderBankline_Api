package com.bank.application.usecaseimpl;

import com.bank.application.gateway.TaxNumberAvailableGateway;
import com.bank.core.exception.TaxNumberException;
import com.bank.core.exception.enums.ErrorCodeEnum;
import com.bank.usecase.TaxNumberAvailableUseCase;
import reactor.core.publisher.Mono;

public class TaxNumberAvailableUseCaseImpl implements TaxNumberAvailableUseCase {
    private TaxNumberAvailableGateway taxNumberAvailableGateway;

    public TaxNumberAvailableUseCaseImpl(TaxNumberAvailableGateway taxNumberAvailableGateway) {
        this.taxNumberAvailableGateway = taxNumberAvailableGateway;
    }

    @Override
    public Mono<Boolean> taxNumberAvaliable(String taxNumber) throws TaxNumberException {

        return taxNumberAvailableGateway.taxNumberAvailable(taxNumber).onErrorComplete(throwable -> {
            try {
                throw new TaxNumberException(ErrorCodeEnum.ON0003.getMessage(), ErrorCodeEnum.ON0003.getCode());
            } catch (TaxNumberException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
