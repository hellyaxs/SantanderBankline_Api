package com.bank.application.gateway;

import com.bank.core.domain.Transaction;

public interface UserNotificationGateway {
    Boolean notificate(Transaction transaction, String email);
}
