package ru.bellintegrator.practice.employee.controller;

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
import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import ru.bellintegrator.practice.employee.view.report.ReportView;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@DirtiesContext
@ActiveProfiles("test")
public class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();
    private OrganizationView organization;
    private OfficeView office;

    @Before
    public void setUp() {
        OrganizationToSave organizationToSave = new OrganizationToSave();
        organizationToSave.name = "ООО КонстантаПлюс";
        organizationToSave.fullName = "Общество с ограниченной ответственностью КонстантаПлюс";
        organizationToSave.inn = "0101010101";
        organizationToSave.kpp = "010101010";
        organizationToSave.address = "ул. Заки Валиди, д.33";
        organizationToSave.countryCode = 643;
        organizationToSave.phone = "7 (912) 313-17-44";
        organizationToSave.isActive = true;

        HttpEntity<OrganizationToSave> entityOrganizationToSave = new HttpEntity<>(organizationToSave, headers);
        organization = restTemplate.postForObject("/api/organization/create", entityOrganizationToSave, OrganizationView.class);

        OfficeToSave officeToSave = new OfficeToSave();
        officeToSave.name = "Офис в Центре";
        officeToSave.address = "г. Казань, ул. Габдуллы Тукая, д. 46";
        officeToSave.phone = "7 (911) 333-12-44";
        officeToSave.isActive = true;
        officeToSave.orgId = organization.id;

        HttpEntity<OfficeToSave> entityOfficeToSave = new HttpEntity<>(officeToSave, headers);
        office = restTemplate.postForObject("/api/office/create", entityOfficeToSave, OfficeView.class);

    }

    @Test
    public void test() {
        Long officeId = office.id;

        EmployeeToSave employeeToSave = new EmployeeToSave();
        employeeToSave.firstName = "Костян";
        employeeToSave.secondName = "Лопатин";
        employeeToSave.middleName = "Владимирович";
        employeeToSave.position = "Инженер";
        employeeToSave.phone = "8 (999) 120-33-78";
        employeeToSave.docNumber = "0911987651";
        employeeToSave.docCode = 21;
        employeeToSave.docDate = new Date(1521809079);
        employeeToSave.citizenshipCode = 643;
        employeeToSave.officeId = officeId;

        HttpEntity<EmployeeToSave> entityEmployeeToSave = new HttpEntity<>(employeeToSave, headers);
        EmployeeView employeeView = restTemplate.postForObject("/api/employee/create", entityEmployeeToSave, EmployeeView.class);
        Long employeeId = employeeView.id;
        assertNotNull(employeeId);

        EmployeeFilter filter = new EmployeeFilter();
        filter.officeId = officeId;
        filter.citizenshipCode = employeeToSave.citizenshipCode;

        HttpEntity<EmployeeFilter> entityFilter = new HttpEntity<>(filter, headers);
        List<Map<String, Object>> employeeViewList = restTemplate.postForObject("/api/employee/list", entityFilter, List.class);
        assertEquals(employeeToSave.position, employeeViewList.get(0).get("position"));

        EmployeeView employeeToUpdate = new EmployeeView();
        employeeToUpdate.id = employeeId;
        employeeToUpdate.firstName = "Тимур";
        employeeToUpdate.secondName = "Кличко";
        employeeToUpdate.middleName = "Владимирович";
        employeeToUpdate.position = "Инженер";
        employeeToUpdate.phone = "8 (999) 120-33-78";
        employeeToUpdate.docNumber = "0911987651";
        employeeToUpdate.docCode = 21;
        employeeToUpdate.docDate = new Date(1521809079);
        employeeToUpdate.citizenshipCode = 643;
        employeeToUpdate.officeId = officeId;

        HttpEntity<EmployeeView> entityEmployeeToUpdate = new HttpEntity<>(employeeToUpdate, headers);
        restTemplate.postForObject("/api/employee/update", entityEmployeeToUpdate, Void.class);
        EmployeeView employeeLoadedById = restTemplate.getForObject("/api/employee/" + employeeId, EmployeeView.class);
        assertEquals(employeeToUpdate.secondName, employeeLoadedById.secondName);

    }

    @Test
    public void testReport() {
        EmployeeToSave employee1 = new EmployeeToSave();
        employee1.firstName = "Костян";
        employee1.secondName = "Лопатин";
        employee1.middleName = "Владимирович";
        employee1.position = "Инженер";
        employee1.registrationDate = new Date(1521971313);
        employee1.salary = new BigDecimal(124000);
        employee1.phone = "8 (999) 120-33-78";
        employee1.docNumber = "0911987651";
        employee1.docCode = 21;
        employee1.docDate = new Date(1431510513);
        employee1.citizenshipCode = 643;
        employee1.officeId = office.id;

        EmployeeToSave employee2 = new EmployeeToSave();
        employee2.firstName = "Виктор";
        employee2.secondName = "Никитин";
        employee2.middleName = "Дмитриевич";
        employee2.position = "Инженер";
        employee2.registrationDate = new Date(1522057713);
        employee2.salary = new BigDecimal(124500);
        employee2.phone = "8 (999) 120-33-78";
        employee2.docNumber = "0911987651";
        employee2.docCode = 21;
        employee2.docDate = new Date(1431510513);
        employee2.citizenshipCode = 643;
        employee2.officeId = office.id;

        HttpEntity<EmployeeToSave> entity = new HttpEntity<>(employee1, headers);
        restTemplate.postForObject("/api/employee/create", entity, EmployeeView.class);

        entity = new HttpEntity<>(employee2, headers);
        restTemplate.postForObject("/api/employee/create", entity, EmployeeView.class);

        ReportFilter filter = new ReportFilter();
        filter.dateFrom = new Date(1521884913); //2018-03-24
        filter.dateTo = new Date(1522144113); //2018-03-27
        filter.salaryFrom = new BigDecimal(122000);
        filter.salaryTo = new BigDecimal(131100);

        HttpEntity<ReportFilter> entityFilter = new HttpEntity<>(filter, headers);
        ReportView reportView = restTemplate.postForObject("/api/employee/report", entityFilter, ReportView.class);
        assertEquals(new Integer(1), reportView.organizationsCount);
        assertEquals(new Integer(1), reportView.officesCount);
        assertEquals(new Integer(2), reportView.employeesCount);

    }
}