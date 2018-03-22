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
}
