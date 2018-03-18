package ru.bellintegrator.practice.office.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeServiceTest {

    @Autowired
    private OfficeService service;

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWhenIdIsNull() {
        service.loadById(null);

    }

    @Test(expected = NullPointerException.class)
    public void testExceptionWhenOfficeIsNotExists() {
        service.loadById(-1000l);
    }
}