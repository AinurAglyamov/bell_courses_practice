package ru.bellintegrator.practice.organization.dao;

import org.junit.Before;
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

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDaoTest {

    @Autowired
    private OrganizationDao dao;

    private Organization organization;
    private Office office;

    @Before
    public void initTest() {
        organization = new Organization();

        organization.setName("ООО Открытие");
        organization.setFullName("Общество с ограниченной ответственностью Открытие");
        organization.setInn("0101010101");
        organization.setKpp("010101010");
        organization.setAddress("Grove Street");
        organization.setPhone("7 (911) 333-12-44");
        organization.setActive(true);

        office = new Office();

        office.setName("Офис в Затоне");
        office.setAddress("г. Уфа, ул. Успенского, д. 33");
        office.setPhone("+7 (347) 271-12-35");
        office.setActive(true);

        organization.addOffice(office);
    }

    @Test
    public void testLoadById() {
        dao.save(organization);

        Long id = organization.getId();
        String name = organization.getName();

        assertNotNull(dao.loadById(id));
        assertEquals(name, dao.loadById(id).getName());
    }

    @Test
    public void testSave() {
        dao.save(organization);

        assertNotNull(organization.getId());
        assertNotNull(office.getId());
    }

    @Test
    public void testUpdate() {
        dao.save(organization);

        Long id = organization.getId();
        String newName = "ООО Закрытие";
        String newFullName = "Общество с ограниченной ответственностью Закрытие";

        Organization newOrganization = dao.loadById(id);
        newOrganization.setName(newName);
        newOrganization.setFullName(newFullName);

        dao.update(newOrganization);

        Organization updatedOrganization = dao.loadById(id);

        assertEquals(newName, updatedOrganization.getName());
        assertEquals(newFullName, updatedOrganization.getFullName());

    }

    @Test
    public void testDelete() {
        dao.save(organization);

        Long id = organization.getId();

        dao.delete(id);

        assertNull(dao.loadById(id));
    }

    @Test
    public void testList() {
        dao.save(organization);

        Organization organization1 = new Organization();
        organization1.setName("ООО Открытие");
        organization1.setInn("0101010101");
        organization1.setActive(true);

        List<Organization> organizations = dao.list(organization1);

        assertFalse(organizations.isEmpty());
        assertEquals(organization1.getName(), organizations.get(0).getName());

        Organization organization2 = new Organization();
        organization2.setName("ООО НеОткрытие");
        organization2.setInn("0101010101");
        organization2.setActive(true);

        assertTrue(dao.list(organization2).isEmpty());

    }
}