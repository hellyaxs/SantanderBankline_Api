package com.bank.infrastructure.services;

import com.bank.application.gateway.UserNotificationGateway;
import com.bank.core.domain.Transaction;
import com.bank.infrastructure.client.usernotificate.NotificateClientService;
import org.springframework.stereotype.Service;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class UserNotificationGatewayImpl implements UserNotificationGateway {
    private NotificateClientService notificateClientService;

    public UserNotificationGatewayImpl(NotificateClientService notificateClientService) {
        this.notificateClientService = notificateClientService;
    }

    @Override
    public Boolean notificate(Transaction transaction, String email) {
        log.info("Iniciando processo de notificação do usuário::UserNotificationGatewayImpl");
        var response = notificateClientService.notificate();

        if (response == true) {
            log.info("Usuário notificado com sucesso::UserNotificationGatewayImpl");
        }

        return response;
    }
}
