package ru.antowka.stock.application.representation.transaction;

import lombok.Data;

import java.util.List;

/**
 * Wrapper for request transaction delete
 */
@Data
public class TransactionDeleteRq {
    List<TransactionCommand> transactions;
}
