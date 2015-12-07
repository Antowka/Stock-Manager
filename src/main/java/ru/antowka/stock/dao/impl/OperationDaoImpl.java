package ru.antowka.stock.dao.impl;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.antowka.stock.dao.OperationDao;
import ru.antowka.stock.model.Operation;
import ru.antowka.stock.model.OperationType;
import ru.antowka.stock.model.Ticker;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Anton Nik on 29.11.15.
 */
@Repository
public class OperationDaoImpl implements OperationDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Save new operation to db
     *
     * @param operation
     * @return
     */
    @Override
    @Transactional
    public Operation addOperation(Operation operation) {

        try {
            //insert new operation
            sessionFactory
                    .getCurrentSession()
                    .saveOrUpdate(operation);

            //get full operation type
            operation.setOperationType(
                    (OperationType) sessionFactory
                            .getCurrentSession()
                            .createCriteria(OperationType.class)
                            .add(Restrictions.eq("operationTypeId", operation.getOperationType().getOperationTypeId()))
                            .setMaxResults(1)
                            .uniqueResult()
            );

            //get full ticker
            operation.setTicker(
                    (Ticker) sessionFactory
                            .getCurrentSession()
                            .createCriteria(Ticker.class)
                            .add(Restrictions.eq("tickerId", operation.getTicker().getTickerId()))
                            .setMaxResults(1)
                            .uniqueResult()
            );

        }catch(HibernateException e){

            e.getStackTrace();
        }

        return operation;
    }

    @Override
    @Transactional
    public Operation updateOperation(Operation operation) {
        return null;
    }

    @Override
    @Transactional
    public Operation removeOperation(Operation operation) {
        return null;
    }

    @Override
    @Transactional
    public List<Operation> getOperationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
