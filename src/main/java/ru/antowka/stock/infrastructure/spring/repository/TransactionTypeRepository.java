package ru.antowka.stock.infrastructure.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.antowka.stock.domain.model.transaction.TransactionType;

/**
 * Repository for CRUD operation with transactionTypes
 */
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    TransactionType findByName(String name);
}
