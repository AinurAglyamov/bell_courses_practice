package ru.bellintegrator.practice.organization.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDaoTest {

    @Autowired
    private OrganizationDao dao;

    @Test
    public void testLoadById() {

    }

    @Test
    public void testSave() {
        Organization organization = new Organization();
        List<Office> offices = new ArrayList<>();

        organization.setName("ООО Открытие");
        organization.setFullName("Общество с ограниченной ответственностью Открытие");
        organization.setInn("0101010101");
        organization.setKpp("010101010");
        organization.setAddress("Groove Street");
        organization.setPhone("7 (911) 333-12-44");
        organization.setActive(true);

        dao.save(organization);

        assertNotNull(organization.getId());
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}