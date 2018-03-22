package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

import java.util.List;

public interface UserDao {

    /**
     *
     * save User
     *
     * @param user
     */
    void save(User user);

    /**
     *
     * get Users by login
     *
     * @param login
     * @return User value
     */
    List<User> findByLogin(String login);


    /**
     *
     * get User by code
     *
     * @param code
     * @return user value
     */
    User findByCode(String code);

    /**
     *
     * get User by login and password
     *
     * @param login
     * @param password
     * @return User value
     */
    User findByLoginAndPassword(String login, String password);
}
