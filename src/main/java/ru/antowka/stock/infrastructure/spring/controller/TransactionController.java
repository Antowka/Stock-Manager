package ru.antowka.stock.infrastructure.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.antowka.stock.application.exception.TransactionException;
import ru.antowka.stock.application.mapper.transaction.TransactionMapper;
import ru.antowka.stock.application.representation.StatusAction;
import ru.antowka.stock.application.representation.transaction.TransactionCommand;
import ru.antowka.stock.application.representation.transaction.TransactionDeleteRq;
import ru.antowka.stock.application.representation.transaction.TransactionRepresentation;
import ru.antowka.stock.application.service.TransactionService;
import ru.antowka.stock.domain.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Transaction controller
 */
@Controller
@RequestMapping("/api/transaction")
public class TransactionController {


    private TransactionMapper transactionMapper;

    private TransactionService transactionService;

    private final static Logger logger = Logger.getLogger(TransactionController .class);


    @Autowired
    public TransactionController(TransactionMapper transactionMapper, TransactionService transactionService) {
        this.transactionMapper = transactionMapper;
        this.transactionService = transactionService;
    }

    /**
     * Create new transaction
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    TransactionRepresentation create(@RequestBody TransactionCommand command) {

        if (command.getId() != null) {
            logger.warn("Wrong update operation in create action. With ID: " + command.getId());
            throw new TransactionException("Wrong update operation in create action");
        }

        Transaction transaction = transactionService
                .create(transactionMapper.toEntity(command));

        return transactionMapper.toRepresentation(transaction);
    }

    /**
     * Delete exist transaction
     *
     * @param transactionDeleteRq
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody StatusAction delete(@RequestBody TransactionDeleteRq transactionDeleteRq) {

        List<Transaction> transactions = new ArrayList<>();

        for (TransactionCommand transactionCommand : transactionDeleteRq.getTransactions()) {
            if (transactionCommand.getId() == null) {
                throw new TransactionException("Wrong request for remove transaction");
            }
            transactions.add(transactionMapper.toEntity(transactionCommand));
        }

        return transactionService.delete(transactions);
    }
}
