package ru.antowka.stock.application.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.stock.application.representation.StatusAction;
import ru.antowka.stock.domain.model.transaction.Transaction;
import ru.antowka.stock.infrastructure.spring.repository.TransactionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for transaction
 */
@Service
public class TransactionService {


    private TransactionRepository transactionRepository;

    private final static Logger logger = Logger.getLogger(TransactionService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * For create new transaction
     *
     * @param transaction
     * @return
     */
    @Transactional
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     *  For delete transaction
     *
     * @param transactions
     * @return
     */
    public StatusAction delete(List<Transaction> transactions) {

        boolean isRemoved = true;

        transactionRepository.deleteInBatch(transactions);

        for (Transaction transaction : transactions) {

            if (transactionRepository.exists(transaction.getId())) {
                isRemoved = false;
                break;
            }
        }

        StatusAction statusAction = new StatusAction();
        statusAction.setSuccess(isRemoved);
        statusAction.setMessage("Transaction remove is " + (isRemoved?"success":"fail"));

        return statusAction;
    }
}
