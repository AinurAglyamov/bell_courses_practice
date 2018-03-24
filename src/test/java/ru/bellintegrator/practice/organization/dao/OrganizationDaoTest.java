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
import ru.bellintegrator.practice.organization.error.OrganizationNotFoundException;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.reference.dao.CountryDao;

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
    private OrganizationDao organizationDao;

    @Autowired
    private CountryDao countryDao;

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
        organization.setCountry(countryDao.findByCode(643));
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
        organizationDao.save(organization);

        Long id = organization.getId();
        String name = organization.getName();

        assertNotNull(organizationDao.loadById(id));
        assertEquals(name, organizationDao.loadById(id).getName());
    }

    @Test
    public void testSave() {
        organizationDao.save(organization);

        assertNotNull(organization.getId());
        assertNotNull(office.getId());
    }

    @Test
    public void testUpdate() {
        organizationDao.save(organization);

        Long id = organization.getId();
        String newName = "ООО Закрытие";
        String newFullName = "Общество с ограниченной ответственностью Закрытие";

        Organization newOrganization = organizationDao.loadById(id);
        newOrganization.setName(newName);
        newOrganization.setFullName(newFullName);

        organizationDao.update(newOrganization);

        Organization updatedOrganization = organizationDao.loadById(id);

        assertEquals(newName, updatedOrganization.getName());
        assertEquals(newFullName, updatedOrganization.getFullName());

    }

    @Test(expected = OrganizationNotFoundException.class)
    public void testDelete() {
        organizationDao.save(organization);

        Long id = organization.getId();

        organizationDao.delete(id);

        organizationDao.loadById(id);
    }

    @Test
    public void testList() {
        organizationDao.save(organization);

        Organization organization1 = new Organization();
        organization1.setName("ООО Открытие");
        organization1.setInn("0101010101");
        organization1.setActive(true);

        List<Organization> organizations = organizationDao.list(organization1);

        assertFalse(organizations.isEmpty());
        assertEquals(organization1.getName(), organizations.get(0).getName());

        Organization organization2 = new Organization();
        organization2.setName("ООО НеОткрытие");
        organization2.setInn("0101010101");
        organization2.setActive(true);

        assertTrue(organizationDao.list(organization2).isEmpty());

    }
}