package ru.antowka.stock.infrastructure.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.antowka.stock.domain.model.transaction.Transaction;

import java.util.List;

/**
 * Repository for CRUD operation with transactions
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTickerNameStartingWith(String tickerName);
}
