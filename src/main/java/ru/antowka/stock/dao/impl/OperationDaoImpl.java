package ru.antowka.stock.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.stock.dao.OperationDao;
import ru.antowka.stock.model.Operation;

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
    public Operation addOperation(Operation operation) {

        sessionFactory.getCurrentSession()
                        .saveOrUpdate(operation);

        return operation;
    }

    @Override
    public Operation updateOperation(Operation operation) {
        return null;
    }

    @Override
    public Operation removeOperation(Operation operation) {
        return null;
    }

    @Override
    public List<Operation> getOperationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
