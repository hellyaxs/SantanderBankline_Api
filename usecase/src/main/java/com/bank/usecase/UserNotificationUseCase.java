package com.bank.usecase;

import com.bank.core.domain.Transaction;

public interface UserNotificationUseCase {
    Boolean notificate(Transaction transaction, String email);
}
