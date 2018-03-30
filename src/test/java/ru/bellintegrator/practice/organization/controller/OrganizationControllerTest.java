package ru.bellintegrator.practice.organization.controller;

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
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationFilter;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@DirtiesContext
@ActiveProfiles("test")
public class OrganizationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void test() {
        OrganizationToSave organization = new OrganizationToSave();
        organization.name = "ООО КонстантаПлюс";
        organization.fullName = "Общество с ограниченной ответственностью КонстантаПлюс";
        organization.inn = "0101010101";
        organization.kpp = "010101010";
        organization.address = "ул. Заки Валиди, д.33";
        organization.countryCode = 643;
        organization.phone = "7 (912) 313-17-44";
        organization.isActive = true;

        HttpEntity<OrganizationToSave> entityToSave = new HttpEntity<>(organization, headers);
        OrganizationView view = restTemplate.postForObject("/api/organization/create", entityToSave, OrganizationView.class);
        assertNotNull(view.id);

        OrganizationFilter filter = new OrganizationFilter();
        filter.name = organization.name;

        HttpEntity<OrganizationFilter> entityFilter = new HttpEntity<>(filter, headers);
        List<Map<String,Object>> organizationViewList = restTemplate.postForObject("/api/organization/list", entityFilter, List.class);
        assertEquals(organizationViewList.get(0).get("name"), organization.name);

        OrganizationView organizationLoadedById = restTemplate.getForObject("/api/organization/" + view.id, OrganizationView.class);
        assertEquals(organizationLoadedById.id, view.id);

        OrganizationView organizationToUpdate = new OrganizationView();
        organizationToUpdate.id = view.id;
        organizationToUpdate.name = "ООО КонстантаМинус";
        organizationToUpdate.fullName = "Общество с ограниченной ответственностью КонстантаМинус";
        organizationToUpdate.inn = "0101010101";
        organizationToUpdate.kpp = "010101010";
        organizationToUpdate.address = "ул. Заки Валиди, д.33";
        organizationToUpdate.countryCode = 643;
        organizationToUpdate.phone = "7 (912) 313-17-44";
        organizationToUpdate.isActive = true;

        HttpEntity<OrganizationView> entityToUpdate = new HttpEntity<>(organizationToUpdate, headers);
        restTemplate.postForObject("/api/organization/update", entityToUpdate, Void.class);

        organizationLoadedById = restTemplate.getForObject("/api/organization/" + organizationToUpdate.id, OrganizationView.class);
        assertEquals(organizationToUpdate.name, organizationLoadedById.name);


    }




}