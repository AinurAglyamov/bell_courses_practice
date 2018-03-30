package ru.bellintegrator.practice.user.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.user.controller.UserController;
import ru.bellintegrator.practice.user.service.ActivationService;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserLoginView;
import ru.bellintegrator.practice.user.view.UserView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "User controller API")
public class UserControllerImpl implements UserController{

    private UserService userService;
    private ActivationService activationService;

    @Autowired
    public UserControllerImpl(UserService userService, ActivationService activationService) {
        this.userService = userService;
        this.activationService = activationService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "register User", httpMethod = "POST")
    @PostMapping("/register")
    public void register(@RequestBody UserView user) {
        userService.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "activate User", httpMethod = "GET")
    @GetMapping("/activation")
    public void activate(@RequestParam String code) {
        activationService.activate(code);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "login User", httpMethod = "POST")
    @PostMapping("/login")
    public void login(@RequestBody UserLoginView user) {
        userService.login(user);
    }
}

