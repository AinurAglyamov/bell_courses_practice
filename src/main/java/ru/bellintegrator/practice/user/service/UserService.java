package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.view.UserView;

public interface UserService {

    /**
     *
     * save User
     *
     * @param view
     */
    void save(UserView view);

    /**
     * activate User
     *
     * @param activationCode
     */
    void activate(String activationCode);

    /**
     *
     * login User
     *
     * @param view
     */
    void login(UserView view);
}
