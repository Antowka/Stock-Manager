package ru.antowka.stock.dao;

import ru.antowka.stock.model.Operation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Anton Nik on 26.11.15.
 */
public interface OperationDao {

    Operation addOperation(Operation operation);

    Operation updateOperation(Operation operation);

    Operation removeOperation(Operation operation);

    List<Operation> getOperationsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
