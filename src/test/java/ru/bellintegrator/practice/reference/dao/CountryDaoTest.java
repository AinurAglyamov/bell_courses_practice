package ru.bellintegrator.practice.reference.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.reference.model.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class CountryDaoTest {

    @Autowired
    private CountryDao countryDao;

    @Test
    public void testUpdateCountries(){
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(36,"Австралия"));
        countries.add(new Country(124, "Канада"));
        countries.add(new Country(643, "Россия"));
        countries.add(new Country(112, "Республика Беларусь"));
        countries.add(new Country(203, "Чехия"));
        countries.add(new Country(352, "Исландия"));

        countryDao.updateCountries(countries);

        assertTrue(countryDao.all().containsAll(countries));
    }

}