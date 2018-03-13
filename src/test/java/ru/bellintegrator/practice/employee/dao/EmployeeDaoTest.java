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
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.model.DocumentType;

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
        employee.setIdentified(true);

        office = new Office();

        office.setName("Офис в Центре");
        office.setAddress("г. Казань, ул. Габдуллы Тукая, д. 46");
        office.setPhone("+7 (843) 511-72-25");
        office.setActive(true);

        documentType = new DocumentType(10, "Паспорт гражданина Грузии");
        country = new Country(268,"Грузия");

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
        String newCitizenshipName = "Республика Грузия";

        Employee newEmployee = employeeDao.loadById(id);
        newEmployee.setPosition(newPosition);
        newEmployee.getCountry().setName(newCitizenshipName);

        employeeDao.update(newEmployee);

        Employee updatedEmployee = employeeDao.loadById(id);

        assertEquals(newPosition, updatedEmployee.getPosition());
        assertEquals(newCitizenshipName, updatedEmployee.getCountry().getName());
    }

    @Test
    public void testDelete() {
        Long id = employee.getId();

        employeeDao.delete(id);

        assertNull(employeeDao.loadById(id));
    }

    @Test
    public void testList() {
        //officeDao.save(office);

        Employee employee1 = new Employee();
        employee1.setOffice(office);
        employee1.setFirstName("Валерий");
        employee1.setSecondName("Меладзе");
        employee1.getCountry().setCode(268);

        List<Employee> employees = employeeDao.list(employee1);

        assertFalse(employees.isEmpty());

        Employee employee2 = new Employee();
        employee2.setOffice(office);
        employee2.setFirstName("Валерий");
        employee2.setSecondName("Меладзе");
        employee2.getCountry().setCode(267);

        assertTrue(employeeDao.list(employee2).isEmpty());

    }
}