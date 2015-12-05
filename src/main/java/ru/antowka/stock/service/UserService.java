package ru.antowka.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.antowka.stock.dao.UserDao;
import ru.antowka.stock.model.User;

import java.security.Principal;


/**
 * Created by Anton Nik on 06.12.15.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Method response current user
     *
     * @return
     */
    public User getCurrentUser(){

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if(principal instanceof UserDetails) {

            return userDao.getUserByUsername(((UserDetails)principal).getUsername());
        } else {

            return userDao.getUserByUsername(principal.toString());
        }
    }
}
