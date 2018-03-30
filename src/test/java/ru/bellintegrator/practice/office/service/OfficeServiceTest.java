package ru.bellintegrator.practice.office.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.error.OfficeNotFoundException;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.impl.OfficeServiceImpl;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.practice.reference.model.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OfficeServiceTest {

    @TestConfiguration
    static class OfficeServiceImplTestContextConfiguration {

        @Bean
        public OfficeService officeService(){
            return new OfficeServiceImpl();
        }

    }

    @Mock
    private OfficeDao officeDao;

    @Mock
    private OrganizationDao orgDao;

    @Autowired
    @InjectMocks
    private OfficeService service;


    private Organization organization;

    @Before
    public void setUp(){
        organization = new Organization();

        organization.setId(1l);
        organization.setName("ООО Открытие");
        organization.setFullName("Общество с ограниченной ответственностью Открытие");
        organization.setInn("0101010101");
        organization.setKpp("010101010");
        organization.setAddress("Grove Street");
        organization.setCountry(new Country(643, "Российская федерация"));
        organization.setPhone("7 (911) 333-12-44");
        organization.setActive(true);
    }

    @Test
    public void testLoadById(){
        Office office = new Office();

        office.setId(1l);
        office.setName("Офис в Центре");
        office.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office.setPhone("+7 (843) 511-72-25");
        office.setActive(true);
        office.setOrganization(organization);

        when(officeDao.loadById(1l)).thenReturn(office);

        OfficeView officeView = service.loadById(1l);

        assertEquals(office.getPhone(), officeView.phone);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadByIdWhenIdIsNull() {
        service.loadById(null);

    }

    @Test(expected = OfficeNotFoundException.class)
    public void testExceptionWhenOfficeIsNotExists() {
        when(officeDao.loadById(-1000l)).thenThrow(OfficeNotFoundException.class);

        service.loadById(-1000l);
    }

    @Test
    public void testSave(){
        OfficeToSave view = new OfficeToSave();
        view.name = "Офис в Центре";
        view.address = "г. Казань, ул. Габдуллы Тукая, д. 46";
        view.phone = "8 (843) 511-72-25";
        view.isActive = true;
        view.orgId = 1l;

        when(orgDao.loadById(view.orgId)).thenReturn(organization);
        doNothing().when(officeDao).save(any(Office.class));

        service.save(view);

        verify(officeDao).save(any(Office.class));
    }

    @Test
    public void testUpdate(){
        OfficeView view = new OfficeView();
        view.id = 1l;
        view.name = "Офис в Центре";
        view.address = "г. Казань, ул. Габдуллы Тукая, д. 46";
        view.phone = "8 (843) 511-72-25";
        view.isActive = true;

        doNothing().when(officeDao).update(any(Office.class));

        service.update(view);

        verify(officeDao).update(any(Office.class));
    }

    @Test
    public void testDelete(){
        Long id = 1l;

        doNothing().when(officeDao).delete(id);

        service.delete(id);

        verify(officeDao).delete(id);
    }

    @Test(expected = OfficeNotFoundException.class)
    public void testDeleteWhenIdIsWrong(){
        Long id = -1l;

        doThrow(new OfficeNotFoundException(id)).when(officeDao).delete(id);

        service.delete(id);
    }

    @Test
    public void testList(){
        Office office1 = new Office();
        office1.setName("Офис в Центре");
        office1.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office1.setPhone("+7 (843) 511-72-25");
        office1.setActive(true);
        office1.setOrganization(organization);

        Office office2 = new Office();
        office2.setName("Офис на Окраине");
        office2.setAddress("г. Казань, ул. Шайхзады Бабича, д. 11");
        office2.setPhone("+7 (843) 713-64-33");
        office2.setActive(true);
        office2.setOrganization(organization);

        List<Office> offices = new ArrayList<>();
        offices.add(office1);
        offices.add(office2);

        when(officeDao.list(any(Office.class))).thenReturn(offices);

        OfficeFilter filter = new OfficeFilter();
        filter.orgId = 1l;

        List<OfficeView> officeViews = service.list(filter);

        assertEquals(office1.getName(), officeViews.get(0).name);


    }

}