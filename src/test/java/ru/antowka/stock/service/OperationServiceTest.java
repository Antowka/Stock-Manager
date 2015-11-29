package ru.antowka.stock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.stock.dao.OperationDao;
import ru.antowka.stock.model.Operation;
import ru.antowka.stock.model.OperationType;
import ru.antowka.stock.model.Ticker;
import ru.antowka.stock.model.User;

import static org.junit.Assert.*;

/**
 * Created by Anton Nik on 29.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    @InjectMocks
    private OperationService operationService;

    @Mock
    private OperationDao operationDao;

    @Test
    public void testAddOperation() throws Exception {

        Operation operation = new Operation();
        operation.setOperationId(1);
        operation.setAmount(1);
        operation.setOperationType(new OperationType());
        operation.setTicker(new Ticker());
        operation.setUser(new User());

        when(operationDao.addOperation(operation)).thenReturn(operation);

        assertTrue(operation.getOperationId() != 0);
    }
}