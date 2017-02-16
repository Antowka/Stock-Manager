package ru.antowka.stock.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.antowka.stock.infrastructure.spring.repository.TransactionRepository;

/**
 * Service for transaction
 */
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


}
