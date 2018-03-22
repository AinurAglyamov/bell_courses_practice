package ru.bellintegrator.practice.user.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.user.controller.UserController;
import ru.bellintegrator.practice.user.service.SecurityService;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "User controller API")
public class UserControllerImpl implements UserController{

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "register User", httpMethod = "POST")
    @PostMapping("/register")
    public void register(@RequestBody UserView user) {
        user.password = securityService.encode(user.password);
        userService.save(user);
    }
}
