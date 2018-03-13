package ru.bellintegrator.practice.employee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.dao.EmployeeDao;
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.service.EmployeeService;
import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao dao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public EmployeeView loadById(Long id) {
        log.info("id: " + id);

        Employee employee = dao.loadById(id);

        EmployeeView view = new EmployeeView();
        view.firstName = employee.getFirstName();
        view.secondName = employee.getSecondName();
        view.middleName = employee.getMiddleName();
        view.position = employee.getPosition();
        view.phone = employee.getPhone();
        view.docName = employee.getDocumentType().getName();
        view.docNumber = employee.getDocNumber();
        view.docDate = employee.getDocDate();
        view.citizenshipName = employee.getCountry().getName();
        view.citizenshipCode = employee.getCountry().getCode();
        view.isIdentified = employee.isIdentified();

        log.info(view.toString());

        return view;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(EmployeeToSave view) {
        log.info("Employee to save: " + view);

        Employee employee = new Employee();
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.setPhone(view.phone);
        employee.getDocumentType().setCode(view.docCode);
        employee.getDocumentType().setName(view.docName);
        employee.setDocNumber(view.docNumber);
        employee.setDocDate(view.docDate);
        employee.getCountry().setName(view.citizenshipName);
        employee.getCountry().setCode(view.citizenshipCode);
        employee.setIdentified(view.isIdentified);

        dao.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(EmployeeView view) {
        log.info("updated Employee" + view.toString());

        Employee employee = new Employee();

        employee.setId(view.id);
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.setPhone(view.phone);
        employee.getDocumentType().setName(view.docName);
        employee.setDocNumber(view.docNumber);
        employee.setDocDate(view.docDate);
        employee.getCountry().setName(view.citizenshipName);
        employee.getCountry().setCode(view.citizenshipCode);
        employee.setIdentified(view.isIdentified);

        dao.update(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<EmployeeView> list(EmployeeFilter view) {
        log.info("Filter: " + view.toString());

        Employee employee = new Employee();
        employee.getOffice().setId(view.officeId);
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.getDocumentType().setCode(view.docCode);
        employee.getCountry().setCode(view.citizenshipCode);

        List<Employee> employees = dao.list(employee);

        Function<Employee, EmployeeView> mapEmployee = e -> {
            EmployeeView employeeView = new EmployeeView();

            employeeView.id = e.getId();
            employeeView.firstName = e.getFirstName();
            employeeView.secondName = e.getSecondName();
            employeeView.middleName = e.getMiddleName();
            employeeView.position = e.getPosition();

            log.info(employeeView.toString());

            return employeeView;
        };

        return employees.stream().map(mapEmployee).collect(Collectors.toList());
    }
}
