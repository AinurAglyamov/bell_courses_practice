package ru.bellintegrator.practice.organization.service;

import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.error.OrganizationNotFoundException;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.practice.organization.view.OrganizationFilter;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.reference.dao.CountryDao;
import ru.bellintegrator.practice.reference.model.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrganizationServiceTest {

    @TestConfiguration
    static class OrganizationServiceImplTestContextConfiguration {

        @Bean
        public OrganizationService organizationService(){
            return new OrganizationServiceImpl();
        }

    }

    @Autowired
    @InjectMocks
    private OrganizationService service;

    @Mock
    private OrganizationDao organizationDao;

    @Mock
    private CountryDao countryDao;

    private Country country;

    @Before
    public void setUp(){
        country = new Country(643, "Российская федерация");
    }

    @Test
    public void testLoadById(){
        Organization organization = new Organization();

        organization.setId(1l);
        organization.setName("ООО Открытие");
        organization.setFullName("Общество с ограниченной ответственностью Открытие");
        organization.setInn("0101010101");
        organization.setKpp("010101010");
        organization.setAddress("Grove Street");
        organization.setCountry(country);
        organization.setPhone("7 (911) 333-12-44");
        organization.setActive(true);

        when(organizationDao.loadById(1l)).thenReturn(organization);

        OrganizationView organizationView = service.loadById(1l);

        assertEquals(organizationView.name, organization.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWhenIdIsNull() {
        service.loadById(null);

    }

    @Test(expected = OrganizationNotFoundException.class)
    public void testExceptionWhenOrganizationIsNotExists() {
        when(organizationDao.loadById(-1000l)).thenThrow(OrganizationNotFoundException.class);

        service.loadById(-1000l);
    }

    @Test
    public void testSave() {
        OrganizationToSave view = new OrganizationToSave();

        view.name = "ООО Открытие";
        view.fullName = "Общество с ограниченной ответственностью Открытие";
        view.inn = "0101010101";
        view.kpp = "010101010";
        view.address = "Grove Street";
        view.countryCode = 643;
        view.phone = "7 (911) 333-12-44";
        view.isActive = true;

        when(countryDao.findByCode(view.countryCode)).thenReturn(country);
        doNothing().when(organizationDao).save(any(Organization.class));

        service.save(view);

        verify(organizationDao).save(any(Organization.class));
    }

    @Test
    public void testUpdate(){
        OrganizationView view = new OrganizationView();

        view.id = 1l;
        view.name = "ООО Открытие";
        view.fullName = "Общество с ограниченной ответственностью Открытие";
        view.inn = "0101010101";
        view.kpp = "010101010";
        view.address = "Grove Street";
        view.countryCode = 643;
        view.phone = "7 (911) 333-12-44";
        view.isActive = true;

        when(countryDao.findByCode(view.countryCode)).thenReturn(country);
        doNothing().when(organizationDao).update(any(Organization.class));

        service.update(view);

        verify(organizationDao).update(any(Organization.class));
    }

    @Test
    public void testDelete(){
        Long id = 1l;

        doNothing().when(organizationDao).delete(id);

        service.delete(id);

        verify(organizationDao).delete(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWhenIdIsNull(){
        service.delete(null);
    }

    @Test
    public void testList(){
        Organization organization1 = new Organization();

        organization1.setName("ООО Открытие");
        organization1.setFullName("Общество с ограниченной ответственностью Открытие");
        organization1.setInn("0101010101");
        organization1.setKpp("010101010");
        organization1.setAddress("Grove Street");
        organization1.setCountry(country);
        organization1.setPhone("7 (911) 333-12-44");
        organization1.setActive(true);

        Organization organization2 = new Organization();
        organization2.setName("ООО Закрытие");
        organization2.setFullName("Общество с ограниченной ответственностью Закрытие");
        organization2.setInn("5101010101");
        organization2.setKpp("510101010");
        organization2.setAddress("Viktor Street");
        organization2.setCountry(country);
        organization2.setPhone("7 (915) 331-17-38");
        organization2.setActive(true);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);


        OrganizationFilter filter = new OrganizationFilter();

        filter.name = "ООО";

        when(organizationDao.list(any(Organization.class))).thenReturn(organizations);

        List<OrganizationView> organizationViews = service.list(filter);

        assertEquals(organization2.getName(), organizationViews.get(1).name);
    }


}