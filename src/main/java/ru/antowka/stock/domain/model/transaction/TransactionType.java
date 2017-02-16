package ru.antowka.stock.domain.model.transaction;

import lombok.Data;

import javax.persistence.*;

/**
 * Types for transaction (BUY/SELL)
 */
@Data
@Entity
@Table(name = "transaction_types")
public class TransactionType {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
}
