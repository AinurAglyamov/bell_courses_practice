package ru.bellintegrator.practice.user.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.error.UserNotFoundException;
import ru.bellintegrator.practice.user.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ActivateServiceTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EncodingService encodingService;

    @Autowired
    private ActivationService activationService;

    @Test
    public void testActivate() {
        String activationCode = RandomStringUtils.randomAlphanumeric(10);;

        User user = new User();
        user.setLogin("weer");
        user.setPassword("piemlushii");
        user.setName("vlad");
        user.setCode(encodingService.encode(activationCode));
        user.setActive(false);

        userDao.save(user);

        activationService.activate(activationCode);

        assertTrue(user.isActive());

    }

    @Test(expected = UserNotFoundException.class)
    public void testExceptionWhenCodeIsWrong() {
        String activationCode = RandomStringUtils.randomAlphanumeric(10);;

        User user = new User();
        user.setLogin("ainur3");
        user.setPassword("deepexsense");
        user.setName("valera");
        user.setCode(encodingService.encode(activationCode));
        user.setActive(false);

        userDao.save(user);

        activationService.activate("asdasad");
    }
}
