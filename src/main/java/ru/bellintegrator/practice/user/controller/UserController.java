package ru.bellintegrator.practice.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.user.view.UserView;

public interface UserController {

    /**
     *
     * register User
     *
     * @param user
     */
    void register(@RequestBody UserView user);
}
