package ru.bellintegrator.practice.employee.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.employee.error.EmployeeNotFoundException;
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.reference.dao.CountryDao;
import ru.bellintegrator.practice.reference.dao.DocumentTypeDao;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.model.DocumentType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class EmployeeDaoTest {

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DocumentTypeDao documentTypeDao;

    @Autowired
    private CountryDao countryDao;

    private Office office;
    private Employee employee;
    private DocumentType documentType;
    private Country country;

    @Before
    public void setUp() {
        employee = new Employee();

        employee.setFirstName("Валерий");
        employee.setSecondName("Меладзе");
        employee.setMiddleName("Константинович");
        employee.setPosition("Инженер");
        employee.setPhone("+7 (999) 120-33-78");
        employee.setDocNumber("09an987656");
        employee.setDocDate(new Date(1521809079));

        office = new Office();

        office.setName("Офис в Центре");
        office.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office.setPhone("+7 (843) 511-72-25");
        office.setActive(true);

        documentType = documentTypeDao.findByCode(12);
        country = countryDao.findByCode(268);

        employee.setDocumentType(documentType);
        employee.setCountry(country);

        office.addEmployee(employee);
        officeDao.save(office);
        //employeeDao.save(employee);

    }

    @Test
    public void testLoadById() {
        Long id = employee.getId();
        String phone = employee.getPhone();
        String citizenshipName = employee.getCountry().getName();

        Employee employee1 = employeeDao.loadById(id);

        assertNotNull(employee1);
        assertNotNull(country.getId());
        assertNotNull(documentType.getId());
        assertEquals(citizenshipName, employee1.getCountry().getName());
        assertEquals(phone, employee1.getPhone());

    }

    @Test
    public void testSave() {
        employeeDao.save(employee);
        assertNotNull(employee.getId());

    }

    @Test
    public void testUpdate() {
        Long id = employee.getId();
        String newPosition = "Ведущий инженер";

        Employee newEmployee = employeeDao.loadById(id);
        newEmployee.setPosition(newPosition);
        employeeDao.update(newEmployee);

        Employee updatedEmployee = employeeDao.loadById(id);

        assertEquals(newPosition, updatedEmployee.getPosition());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testDelete() {
        Long id = employee.getId();

        employeeDao.delete(id);

        employeeDao.loadById(id);
    }

    @Test
    public void testList() {
        //officeDao.save(office);

        Employee employee1 = new Employee();
        employee1.setOffice(office);
        employee1.setFirstName("Валерий");
        employee1.setSecondName("Меладзе");

        List<Employee> employees = employeeDao.list(employee1);

        assertFalse(employees.isEmpty());

        Employee employee2 = new Employee();
        employee2.setOffice(office);
        employee2.setFirstName("Константин");
        employee2.setSecondName("Меладзе");

        assertTrue(employeeDao.list(employee2).isEmpty());

    }

    @Test
    public void testLoadBySalaryAndDate(){
        Employee employee1 = new Employee();
        employee1.setFirstName("Влад");
        employee1.setSecondName("Александров");
        employee1.setDocNumber("123a443222");
        employee1.setDocDate(new Date(1431510513)); //2015-05-13
        employee1.setSalary(new BigDecimal(124000));
        employee1.setRegistrationDate(new Date(1521971313)); //2018-03-25

        Employee employee2 = new Employee();
        employee2.setFirstName("Валера");
        employee2.setSecondName("Владов");
        employee2.setDocNumber("123a443222");
        employee2.setDocDate(new Date(1403689713)); //2014-06-25
        employee2.setSalary(new BigDecimal(124500));
        employee2.setRegistrationDate(new Date(1522057713)); //2018-03-26

        office.addEmployee(employee1);
        office.addEmployee(employee2);

        ReportFilter filter = new ReportFilter();
        filter.dateFrom = new Date(1521884913); //2018-03-24
        filter.dateTo = new Date(1522144113); //2018-03-27
        filter.salaryFrom = new BigDecimal(123000);
        filter.salaryTo = new BigDecimal(134600);

        List<Employee> employees = employeeDao.loadBySalaryAndRegDate(filter);

        System.out.println(employees);

        assertFalse(employees.isEmpty());

        assertEquals(2, employees.size());



    }
}