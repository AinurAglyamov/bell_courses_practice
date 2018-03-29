package ru.bellintegrator.practice.employee.service;

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
import ru.bellintegrator.practice.employee.dao.EmployeeDao;
import ru.bellintegrator.practice.employee.error.EmployeeNotFoundException;
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.service.impl.EmployeeServiceImpl;
import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import ru.bellintegrator.practice.employee.view.report.ReportView;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.service.impl.OfficeServiceImpl;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.reference.dao.CountryDao;
import ru.bellintegrator.practice.reference.dao.DocumentTypeDao;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.model.DocumentType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService(){
            return new EmployeeServiceImpl();
        }

    }

    @Autowired
    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private OfficeDao officeDao;

    @Mock
    private DocumentTypeDao documentTypeDao;

    @Mock
    private CountryDao countryDao;

    private Organization organization;
    private Office office;
    private DocumentType documentType;
    private Country country;

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

        office = new Office();
        office.setId(1l);
        office.setName("Офис в Центре");
        office.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office.setPhone("8 (843) 511-72-25");
        office.setActive(true);
        office.setOrganization(organization);

        documentType = new DocumentType(21, "Паспорт гражданина РФ");

    }

    @Test
    public void testLoadById(){
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstName("Валерий");
        employee.setSecondName("Меладзе");
        employee.setMiddleName("Константинович");
        employee.setPosition("Инженер");
        employee.setPhone("+7 (999) 120-33-78");
        employee.setDocNumber("09an987656");
        employee.setDocDate(new Date(1521809079));
        employee.setCountry(country);
        employee.setDocumentType(documentType);

        when(employeeDao.loadById(1l)).thenReturn(employee);

        EmployeeView employeeView = service.loadById(1l);

        assertEquals(employee.getMiddleName(), employeeView.middleName);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadByIdWhenIdIsNull() {
        service.loadById(null);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testLoadByIdWhenEmployeeIsNotExists() {
        when(employeeDao.loadById(-1l)).thenThrow(EmployeeNotFoundException.class);
        service.loadById(-1l);
    }

    @Test
    public void testSave(){
        EmployeeToSave view = new EmployeeToSave();
        view.firstName = "Костик";
        view.secondName = "Валерьев";
        view.middleName = "Валерьевич";
        view.position = "Инженер";
        view.phone = "7 (911) 333-12-44";
        view.docNumber = "09an987656";
        view.docDate = new Date(1521809079);
        view.docCode = 21;
        view.citizenshipCode = 643;
        view.officeId = 1l;

        when(officeDao.loadById(view.officeId)).thenReturn(office);
        when(documentTypeDao.findByCode(view.docCode)).thenReturn(documentType);
        when(countryDao.findByCode(view.citizenshipCode)).thenReturn(country);
        doNothing().when(employeeDao).save(any(Employee.class));

        service.save(view);

        verify(employeeDao).save(any(Employee.class));
    }

    @Test
    public void testUpdate(){
        EmployeeView view = new EmployeeView();
        view.id = 1l;
        view.firstName = "Костик";
        view.secondName = "Валерьев";
        view.middleName = "Валерьевич";
        view.position = "Инженер";
        view.phone = "7 (911) 333-12-44";
        view.docNumber = "09an987656";
        view.docDate = new Date(1521809079);
        view.docCode = 21;
        view.citizenshipCode = 643;

        when(documentTypeDao.findByCode(view.docCode)).thenReturn(documentType);
        when(countryDao.findByCode(view.citizenshipCode)).thenReturn(country);

        service.update(view);

        verify(employeeDao).update(any(Employee.class));
    }

    @Test
    public void testDelete(){
        Long id = 1l;

        doNothing().when(employeeDao).delete(id);

        service.delete(id);
    }

    @Test
    public void testList(){
        Employee employee1 = new Employee();
        employee1.setFirstName("Валерий");
        employee1.setSecondName("Меладзе");
        employee1.setMiddleName("Константинович");
        employee1.setPosition("Инженер");
        employee1.setPhone("7 (937) 421-34-81");
        employee1.setDocNumber("0933987656");
        employee1.setDocDate(new Date(1521809079));
        employee1.setCountry(country);
        employee1.setDocumentType(documentType);

        Employee employee2 = new Employee();
        employee2.setFirstName("Константин");
        employee2.setSecondName("Меладзе");
        employee2.setMiddleName("Константинович");
        employee2.setPosition("Инженер");
        employee2.setPhone("+7 (999) 120-33-78");
        employee2.setDocNumber("09an987651");
        employee2.setDocDate(new Date(1521809079));
        employee2.setCountry(country);
        employee2.setDocumentType(documentType);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        EmployeeFilter filter = new EmployeeFilter();
        filter.officeId = 1l;

        when(employeeDao.list(any(Employee.class))).thenReturn(employees);

        List<EmployeeView> employeeViews = service.list(filter);

        assertEquals(employee2.getFullName(), employeeViews.get(1).fullName);
    }

    @Test
    public void testReport(){
        Employee employee1 = new Employee();
        employee1.setFirstName("Влад");
        employee1.setSecondName("Александров");
        employee1.setDocNumber("123a443222");
        employee1.setDocDate(new Date(1431510513));
        employee1.setSalary(new BigDecimal(124000));
        employee1.setRegistrationDate(new Date(1521971313));

        Employee employee2 = new Employee();
        employee2.setFirstName("Валера");
        employee2.setSecondName("Владов");
        employee2.setDocNumber("123a443222");
        employee2.setDocDate(new Date(1403689713));
        employee2.setSalary(new BigDecimal(124500));
        employee2.setRegistrationDate(new Date(1522057713));

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        ReportFilter filter = new ReportFilter();
        filter.dateFrom = new Date(1521884913); //2018-03-24
        filter.dateTo = new Date(1522144113); //2018-03-27
        filter.salaryFrom = new BigDecimal(123000);
        filter.salaryTo = new BigDecimal(134600);

        when(employeeDao.loadBySalaryAndRegDate(filter)).thenReturn(employees);

        ReportView reportView = service.report(filter);

        assertEquals(new Integer(1), reportView.organizationsCount);
        assertEquals(new Integer(1), reportView.officesCount);
        assertEquals(new Integer(2), reportView.employeesCount);
    }
}