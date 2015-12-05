package ru.antowka.stock.dao;

import ru.antowka.stock.model.User;

/**
 * Created by Anton Nik on 06.12.15.
 */
public interface UserDao {

    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
