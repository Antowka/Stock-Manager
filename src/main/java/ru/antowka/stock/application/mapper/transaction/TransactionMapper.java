package ru.antowka.stock.application.mapper.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.antowka.stock.application.mapper.Mapper;
import ru.antowka.stock.application.representation.transaction.TransactionCommand;
import ru.antowka.stock.application.representation.transaction.TransactionRepresentation;
import ru.antowka.stock.domain.model.transaction.Transaction;
import ru.antowka.stock.infrastructure.spring.repository.TickerRepository;
import ru.antowka.stock.infrastructure.spring.repository.TransactionTypeRepository;
import ru.antowka.stock.infrastructure.utils.DateUtils;

/**
 * Mapper for transaction VIEW
 */
@Component
public class TransactionMapper implements Mapper<Transaction, TransactionRepresentation, TransactionCommand> {

    private TickerRepository tickerRepository;

    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionMapper(TickerRepository tickerRepository, TransactionTypeRepository transactionTypeRepository) {
        this.tickerRepository = tickerRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public TransactionRepresentation toRepresentation(Transaction entity) {

        TransactionRepresentation representation = new TransactionRepresentation();
        representation.setId(entity.getId());
        representation.setAmount(entity.getAmount());
        representation.setDate(DateUtils.dateToString(entity.getDate()));
        representation.setPrice(entity.getPrice());
        representation.setComment(entity.getComment());
        representation.setTicker(entity.getTicker().getName());
        representation.setType(entity.getType().getName());

        return representation;
    }

    @Override
    public Transaction toEntity(TransactionCommand command) {

        Transaction transaction = new Transaction();
        transaction.setId(command.getId());
        transaction.setAmount(command.getAmount());

        if (command.getDate() != null) {
            transaction.setDate(DateUtils.stringToDate(command.getDate()));
        }

        transaction.setPrice(command.getPrice());
        transaction.setComment(command.getComment());

        if (command.getTicker() != null) {
            transaction.setTicker(tickerRepository.findByName(command.getTicker()));
        }

        if (command.getType() != null) {
            transaction.setType(transactionTypeRepository.findByName(command.getType()));
        }

        return transaction;
    }
}
