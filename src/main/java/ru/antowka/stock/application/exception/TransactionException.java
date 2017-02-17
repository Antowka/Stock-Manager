package ru.antowka.stock.application.exception;

/**
 * Exception for transaction operation
 */
public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }
}
