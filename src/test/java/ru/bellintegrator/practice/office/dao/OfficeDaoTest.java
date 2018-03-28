package ru.bellintegrator.practice.office.dao;

import org.aspectj.weaver.ast.Or;
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
import ru.bellintegrator.practice.office.error.OfficeNotFoundException;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeDaoTest {

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private OrganizationDao organizationDao;

    private Organization organization;
    private Office office;

    @Before
    public void initTest() {
        office = new Office();

        office.setName("Офис в Центре");
        office.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office.setPhone("7 (843) 511-72-25");
        office.setActive(true);

        organization = new Organization();

        organization.setName("ООО ПриОткрытие");
        organization.setFullName("Общество с ограниченной ответственностью ПриОткрытие");
        organization.setInn("0101010101");
        organization.setKpp("010101010");
        organization.setAddress("3ая улица Строителей");
        organization.setPhone("7 (911) 333-12-44");
        organization.setActive(true);

        organization.addOffice(office);

    }

    @Test
    public void testLoadById() {
        //officeDao.save(office);
        organizationDao.save(organization);

        Long officeId = office.getId();
        String phone = office.getPhone();

        assertNotNull(officeDao.loadById(officeId));
        assertEquals(phone, officeDao.loadById(officeId).getPhone());

    }

    @Test
    public void testSave() {
        assertNull(office.getId());

        officeDao.save(office);

        assertNotNull(office.getId());
    }

    @Test
    public void testUpdate() {
        officeDao.save(office);

        Long id = office.getId();
        String newName = "Офис на Кирова";
        String newAddress = "г. Уфа, ул. Кирова, д. 39";

        Office newOffice = officeDao.loadById(id);
        newOffice.setName(newName);
        newOffice.setAddress(newAddress);

        officeDao.update(office);

        Office updatedOffice = officeDao.loadById(id);

        assertEquals(newName, updatedOffice.getName());
        assertEquals(newAddress, updatedOffice.getAddress());
    }

    @Test(expected = OfficeNotFoundException.class)
    public void testDelete() {
        officeDao.save(office);

        Long id = office.getId();

        officeDao.delete(id);

        officeDao.loadById(id);

    }

    @Test
    public void testList() {
        organizationDao.save(organization);

        Office office1 = new Office();
        office1.setOrganization(organization);
        office1.setName("Офис в Центре");
        office1.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office1.setPhone("+7 (843) 511-72-25");
        office1.setActive(true);

        List<Office> offices = officeDao.list(office1);

        assertFalse(offices.isEmpty());
        assertEquals(office1.getName(), offices.get(0).getName());

        Office office2 = new Office();
        office2.setOrganization(organization);
        office2.setName("Офис не в Центре");
        office2.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office2.setPhone("+7 (843) 511-72-25");
        office2.setActive(true);

        assertTrue(officeDao.list(office2).isEmpty());
    }
}