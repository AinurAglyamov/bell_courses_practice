package ru.bellintegrator.practice.user.dao;


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
import ru.bellintegrator.practice.user.error.UserAlreadyExistsException;
import ru.bellintegrator.practice.user.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test(expected = UserAlreadyExistsException.class)
    public void testExceptionWhenUserAlreadyExists(){
        User user = new User();
        user.setLogin("weer");
        user.setPassword("piemlushii");
        user.setName("vlad");
        user.setActive(true);

        userDao.save(user);
        userDao.save(user);
    }

    @Test
    public void testFindByLogin(){
        User user = new User();
        user.setLogin("vladpiem");
        user.setPassword("piemlushii");
        user.setActive(false);

        userDao.save(user);

        assertTrue(!userDao.findByLogin("vladpiem").isEmpty());
    }
}