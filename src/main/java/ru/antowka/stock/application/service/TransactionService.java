package ru.antowka.stock.application.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.stock.application.representation.StatusAction;
import ru.antowka.stock.domain.model.transaction.Transaction;
import ru.antowka.stock.domain.model.transaction.TransactionType;
import ru.antowka.stock.infrastructure.spring.repository.TransactionRepository;
import ru.antowka.stock.infrastructure.spring.repository.TransactionTypeRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for transaction
 */
@Service
public class TransactionService {

    public static final String TRANSACTION_SELL = "SELL";
    public static final String TRANSACTION_BUY = "BUY";


    private TransactionRepository transactionRepository;
    private TransactionTypeRepository transactionTypeRepository;

    private final static Logger logger = Logger.getLogger(TransactionService.class);

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setTransactionTypeRepository(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    /**
     * Get transaction by id
     *
     * @param id
     * @return
     */
    public Transaction getById(Long id) {
        return transactionRepository.findOne(id);
    }

    /**
     * Return list with transactions
     *
     * @return
     */
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    /**
     * Filter tickers by ticker name
     *
     * @param tickerName
     * @return
     */
    public List<Transaction> getByTickerName(String tickerName) {
        return transactionRepository.findByTickerNameStartingWith(tickerName.toUpperCase());
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
     * For update transaction
     *
     * @param transaction
     * @return
     */
    @Transactional
    public Transaction update(Transaction transaction) {
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

    /**
     * Response transaction type list
     *
     * @return
     */
    public List<TransactionType> getTransactionTypesList() {
        return transactionTypeRepository.findAll();
    }
}
