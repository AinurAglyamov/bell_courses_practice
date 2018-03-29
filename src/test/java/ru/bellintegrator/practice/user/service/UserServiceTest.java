package ru.bellintegrator.practice.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.impl.UserServiceImpl;
import ru.bellintegrator.practice.user.view.UserLoginView;
import ru.bellintegrator.practice.user.view.UserView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }

    }

    @Autowired
    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private EncodingService encodingService;

    @Test
    public void testSave(){
        UserView view = new UserView();
        view.login = "restorator";
        view.password = "oxxxy";

        when(encodingService.encode(view.password)).thenReturn("r4t44y");
        doNothing().when(userDao).save(any(User.class));

        userService.save(view);

        verify(userDao).save(any(User.class));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWhenParametersIsWrong(){
        UserView view = new UserView();
        userService.save(view);
    }

    @Test
    public void testLogin(){
        UserLoginView view = new UserLoginView();
        view.login = "restorator";
        view.password = "oxxxy";

        String encodedPassword = "rtty2";

        User user = new User();
        user.setLogin(view.login);
        user.setPassword(encodedPassword);
        user.setActive(true);

        when(encodingService.encode(view.password)).thenReturn(encodedPassword);
        when(userDao.findByLoginAndPassword(view.login, encodedPassword)).thenReturn(user);

        userService.login(view);

        verify(userDao).findByLoginAndPassword(view.login, encodedPassword);

    }
}