package ru.bellintegrator.practice.employee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.dao.EmployeeDao;
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.service.EmployeeService;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;

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
}
