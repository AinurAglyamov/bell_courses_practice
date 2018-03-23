package ru.bellintegrator.practice.user.service.impl;

import com.google.common.base.Strings;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.error.UserIsNotActivatedException;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.EncodingService;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserLoginView;
import ru.bellintegrator.practice.user.view.UserView;


@Service
public class UserServiceImpl implements UserService{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private EncodingService encodingService;

    public UserServiceImpl(UserDao userDao, EncodingService encodingService) {
        this.userDao = userDao;
        this.encodingService = encodingService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(UserView view) {
        log.info(view.toString());

        checkUser(view);

        String login = view.login;
        String password = encodingService.encode(view.password);
        String name = view.name;
        String email = view.email;
        String randomString = RandomStringUtils.randomAlphanumeric(10);

        log.info("randomString = " + randomString);

        String code = encodingService.encode(randomString);

        log.info("code = " + code);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setCode(code);
        user.setActive(false);

        userDao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void activate(String activationCode) {
        if(Strings.isNullOrEmpty(activationCode)){
            throw new IllegalArgumentException("activationCode is wrong");
        }

        String code = encodingService.encode(activationCode);
        User user = userDao.findByCode(code);
        user.setActive(true);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void login(UserLoginView view) {
        log.info("login = " + view.login + " password = " + view.password);

        //checkUser(view);
        if(Strings.isNullOrEmpty(view.login)){
            throw new IllegalArgumentException("login is wrong");
        }

        if(Strings.isNullOrEmpty(view.password)){
            throw new IllegalArgumentException("password is wrong");
        }

        String login = view.login;
        String password = encodingService.encode(view.password);

        User user = userDao.findByLoginAndPassword(login, password);

        if(!user.isActive()){
            throw new UserIsNotActivatedException("Пользователь не активирован");
        }

    }

    private void checkUser(UserView view) {
        if(Strings.isNullOrEmpty(view.login)){
            throw new IllegalArgumentException("login is wrong");
        }

        if(Strings.isNullOrEmpty(view.password)){
            throw new IllegalArgumentException("password is wrong");
        }

        if((view.name != null) && (!checkName(view.name))){
            throw new IllegalArgumentException("name is wrong");
        }

        if((view.email != null) && (!checkEmail(view.email))){
            throw new IllegalArgumentException("email is wrong");
        }
    }

    private boolean checkName(String name) {
        return name.matches("[A-zА-я]+");
    }

    private boolean checkEmail(String email) {
        return email.matches("[A-Za-z0-9.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }
}
