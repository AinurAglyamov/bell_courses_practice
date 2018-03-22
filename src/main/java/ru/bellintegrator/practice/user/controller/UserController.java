package ru.bellintegrator.practice.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.practice.user.view.UserView;

public interface UserController {

    /**
     *
     * register User
     *
     * @param user
     */
    void register(@RequestBody UserView user);

    /**
     *
     * activate User
     *
     */
    void activate(@RequestParam String code);

    /**
     * login User
     *
     * @param user
     */
    void login(@RequestBody UserView user);
}
