package ru.antowka.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.antowka.stock.dao.OperationDao;
import ru.antowka.stock.model.Operation;
import ru.antowka.stock.model.User;

/**
 * Created by Anton Nik on 29.11.15.
 */
@Service
public class OperationService {

    @Autowired
    private OperationDao operationDao;

    /**
     * Added new operation
     *
     * @param operation
     * @return
     */
    public Operation addOperation(Operation operation){
        //todo - set Principal to User
        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //operation.setUser(user);
        return operationDao.addOperation(operation);
    }
}
