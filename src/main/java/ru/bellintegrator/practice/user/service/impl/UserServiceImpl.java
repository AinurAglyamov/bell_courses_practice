package ru.bellintegrator.practice.user.service.impl;

import com.google.common.base.Strings;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserServiceImpl() {
    }

    @Autowired
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
    public void login(UserLoginView view) {
        log.info("login = " + view.login + " password = " + view.password);

        StringBuilder errorMessage = new StringBuilder(200);

        if(Strings.isNullOrEmpty(view.login)){
            errorMessage.append("login is wrong; ");
        }

        if(Strings.isNullOrEmpty(view.password)){
            errorMessage.append("password is wrong; ");
        }

        if(errorMessage.length() != 0) {
            throw new UserIsNotActivatedException(errorMessage.toString());
        }

        String login = view.login;
        String password = encodingService.encode(view.password);

        log.info("login = " + login + " password = " + password);

        User user = userDao.findByLoginAndPassword(login, password);

        log.info(user.toString());

        if(!user.isActive()){
            throw new UserIsNotActivatedException("Пользователь не активирован");
        }

    }

    private void checkUser(UserView view) {

        StringBuilder errorMessage = new StringBuilder(200);

        if(Strings.isNullOrEmpty(view.login)){
            errorMessage.append("login is wrong; ");
        }

        if(Strings.isNullOrEmpty(view.password)){
            errorMessage.append("password is wrong; ");
        }

        if((view.name != null) && (!checkName(view.name))){
            errorMessage.append("name is wrong; ");
        }

        if((view.email != null) && (!checkEmail(view.email))){
            errorMessage.append("email is wrong; ");
        }

        if(errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    private boolean checkName(String name) {
        return name.matches("[A-zА-я]+");
    }

    private boolean checkEmail(String email) {
        return email.matches("[A-Za-z0-9.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }
}
