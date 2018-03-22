package ru.bellintegrator.practice.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.EncodingService;
import ru.bellintegrator.practice.user.service.UserService;
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

        String login = view.login;
        String password = encodingService.encode(view.password);
        String name = view.name;
        String email = view.email;

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setActive(false);

        userDao.save(user);
    }
}
