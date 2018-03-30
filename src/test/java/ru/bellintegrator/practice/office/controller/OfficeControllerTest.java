package ru.bellintegrator.practice.office.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@DirtiesContext
@ActiveProfiles("test")
public class OfficeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();
    private OrganizationView organization;

    @Before
    public void setUp(){
        OrganizationToSave organizationToSave = new OrganizationToSave();
        organizationToSave.name = "ООО КонстантаПлюс";
        organizationToSave.fullName = "Общество с ограниченной ответственностью КонстантаПлюс";
        organizationToSave.inn = "0101010101";
        organizationToSave.kpp = "010101010";
        organizationToSave.address = "ул. Заки Валиди, д.33";
        organizationToSave.countryCode = 643;
        organizationToSave.phone = "7 (912) 313-17-44";
        organizationToSave.isActive = true;

        HttpEntity<OrganizationToSave> entityToSave = new HttpEntity<>(organizationToSave, headers);
        organization = restTemplate.postForObject("/api/organization/create", entityToSave, OrganizationView.class);
    }


    @Test
    public void test(){
        OfficeToSave officeToSave = new OfficeToSave();
        officeToSave.name = "Офис в Центре";
        officeToSave.address = "г. Казань, ул. Габдуллы Тукая, д. 46";
        officeToSave.phone = "7 (911) 333-12-44";
        officeToSave.isActive = true;
        officeToSave.orgId = organization.id;

        HttpEntity<OfficeToSave> entityToSave = new HttpEntity<>(officeToSave, headers);
        OfficeView view = restTemplate.postForObject("/api/office/create", entityToSave, OfficeView.class);
        Long officeId = view.id;
        assertNotNull(officeId);

        OfficeFilter filter = new OfficeFilter();
        filter.orgId = officeToSave.orgId;
        filter.name = officeToSave.name;

        HttpEntity<OfficeFilter> entityFilter = new HttpEntity<>(filter, headers);
        List<Map<String,Object>> officeViewList = restTemplate.postForObject("/api/office/list", entityFilter, List.class);
        assertEquals(officeToSave.name, officeViewList.get(0).get("name"));

        OfficeView officeLoadedById = restTemplate.getForObject("/api/office/" + officeId, OfficeView.class);
        assertEquals(officeToSave.name, officeLoadedById.name);

        OfficeView officeToUpdate = new OfficeView();
        officeToUpdate.id = officeId;
        officeToUpdate.name = "Офис за Белой";
        officeToUpdate.address = "г. Уфа, ул. Зайнаб Биишевой, д. 18";
        officeToUpdate.phone = "7 (934) 439-09-38";
        officeToUpdate.isActive = true;
        officeToUpdate.orgId = organization.id;

        HttpEntity<OfficeView> entityToUpdate = new HttpEntity<>(officeToUpdate, headers);
        restTemplate.postForObject("/api/office/update", entityToUpdate, Void.class);

        officeLoadedById = restTemplate.getForObject("/api/office/" + officeId, OfficeView.class);
        assertEquals(officeToUpdate.name, officeLoadedById.name);

    }
}