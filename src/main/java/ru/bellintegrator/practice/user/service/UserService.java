package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.view.UserLoginView;
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
     *
     * login User
     *
     * @param view
     */
    void login(UserLoginView view);
}
