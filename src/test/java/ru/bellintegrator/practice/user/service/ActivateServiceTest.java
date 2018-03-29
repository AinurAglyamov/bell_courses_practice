package ru.bellintegrator.practice.user.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.error.UserNotFoundException;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.impl.ActivationServiceImpl;
import ru.bellintegrator.practice.user.service.impl.UserServiceImpl;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ActivateServiceTest {

    @TestConfiguration
    static class ActivationServiceImplTestContextConfiguration {

        @Bean
        public ActivationService activationService(){
            return new ActivationServiceImpl();
        }

    }

    @Autowired
    @InjectMocks
    private ActivationService activationService;

    @Mock
    private UserDao userDao;

    @Mock
    private EncodingService encodingService;


    @Test
    public void testActivate() {
        String activationCode = RandomStringUtils.randomAlphanumeric(10);
        String encodedCode = "rtty123ffz";

        User user = new User();
        user.setLogin("weer");
        user.setPassword("piemlushii");
        user.setName("vlad");
        user.setCode(encodedCode);
        user.setActive(false);

        when(encodingService.encode(activationCode)).thenReturn(encodedCode);
        when(userDao.findByCode(encodedCode)).thenReturn(user);

        activationService.activate(activationCode);

        assertTrue(user.isActive());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testActivateWhenCodeIsNull() {
        activationService.activate(null);
    }

    @Test(expected = UserNotFoundException.class)
    public void testActivateWhenCodeIsWrong() {
        String wrongCode = RandomStringUtils.randomAlphanumeric(10);
        String encodedCode = "rtty123ffz";

        User user = new User();
        user.setLogin("weer");
        user.setPassword("piemlushii");
        user.setName("vlad");
        user.setCode(encodedCode);
        user.setActive(false);

        when(encodingService.encode(wrongCode)).thenReturn(encodedCode);
        when(userDao.findByCode(encodedCode)).thenThrow(new UserNotFoundException("Пользователь с заданным кодом не существует"));

        activationService.activate(wrongCode);
    }
}
