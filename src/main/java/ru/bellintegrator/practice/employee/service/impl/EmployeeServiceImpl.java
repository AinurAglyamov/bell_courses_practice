package ru.bellintegrator.practice.employee.service.impl;

import com.google.common.base.Strings;
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
import ru.bellintegrator.practice.employee.view.report.ReportEmployee;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import ru.bellintegrator.practice.employee.view.report.ReportView;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.reference.dao.CountryDao;
import ru.bellintegrator.practice.reference.dao.DocumentTypeDao;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;
    private OfficeDao officeDao;
    private DocumentTypeDao documentTypeDao;
    private CountryDao countryDao;

    public EmployeeServiceImpl() {
    }

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, OfficeDao officeDao, DocumentTypeDao documentTypeDao, CountryDao countryDao) {
        this.employeeDao = employeeDao;
        this.officeDao = officeDao;
        this.documentTypeDao = documentTypeDao;
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public EmployeeView loadById(Long id) {
        log.info("id: " + id);

        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Employee employee = employeeDao.loadById(id);

        EmployeeView view = new EmployeeView();
        view.id = employee.getId();
        view.officeId = employee.getOffice().getId();
        view.firstName = employee.getFirstName();
        view.secondName = employee.getSecondName();
        view.middleName = employee.getMiddleName();
        view.position = employee.getPosition();
        view.salary = employee.getSalary();
        view.registrationDate = employee.getRegistrationDate();
        view.phone = employee.getPhone();
        view.docCode = employee.getDocumentType().getCode();
        view.docName = employee.getDocumentType().getName();
        view.docNumber = employee.getDocNumber();
        view.docDate = employee.getDocDate();
        view.citizenshipName = employee.getCountry().getName();
        view.citizenshipCode = employee.getCountry().getCode();

        log.info(view.toString());

        return view;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public EmployeeView save(EmployeeToSave view) {
        log.info("Employee to save: " + view);

        if(view.officeId == null) {
            throw new IllegalArgumentException("officeId is null");
        }

        Employee employee = new Employee();
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.setSalary(view.salary);
        employee.setRegistrationDate(view.registrationDate);
        employee.setPhone(view.phone);
        employee.setDocumentType(documentTypeDao.findByCode(view.docCode));
        employee.setDocNumber(view.docNumber);
        employee.setDocDate(view.docDate);
        employee.setCountry(countryDao.findByCode(view.citizenshipCode));

        officeDao.loadById(view.officeId).addEmployee(employee);

        checkEmployee(employee);

        employeeDao.save(employee);

        EmployeeView employeeView = new EmployeeView();
        employeeView.id = employee.getId();

        return employeeView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(EmployeeView view) {
        log.info("updated Employee" + view.toString());

        if (view.id == null) {
            throw new IllegalArgumentException("id is null");
        }/**/

        Employee employee = new Employee();

        employee.setId(view.id);
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.setSalary(view.salary);
        employee.setRegistrationDate(view.registrationDate);
        employee.setPhone(view.phone);
        employee.setDocumentType(documentTypeDao.findByCode(view.docCode));
        employee.setDocNumber(view.docNumber);
        employee.setDocDate(view.docDate);
        employee.setCountry(countryDao.findByCode(view.citizenshipCode));

        checkEmployee(employee);

        employeeDao.update(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("employeeId is null");
        }
        employeeDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<EmployeeView> list(EmployeeFilter view) {
        log.info("Filter: " + view.toString());

        checkFilterParams(view);

        Employee employee = new Employee();
        employee.getOffice().setId(view.officeId);
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.getCountry().setCode(view.citizenshipCode);

        List<Employee> employees = employeeDao.list(employee);

        Function<Employee, EmployeeView> mapEmployee = e -> {
            EmployeeView employeeView = new EmployeeView();

            employeeView.id = e.getId();
            employeeView.fullName = e.getFullName();
            employeeView.position = e.getPosition();
            employeeView.officeName = e.getOffice().getName();

            log.info(employeeView.toString());

            return employeeView;
        };

        return employees.stream().map(mapEmployee).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ReportView report(ReportFilter view) {
        log.info(view.toString());

        checkReportFilterParams(view);

        List<Employee> employees = employeeDao.loadBySalaryAndRegDate(view);
        List<ReportEmployee> reportEmployees = new ArrayList<>(employees.size());
        Set<String> organizations = new HashSet<>();
        Set<String> offices = new HashSet<>();

        for(Employee employee : employees){
            ReportEmployee reportEmployee = new ReportEmployee();
            reportEmployee.orgName = employee.getOffice().getOrganization().getName();
            reportEmployee.officeName = employee.getOffice().getName();
            reportEmployee.employeeName = employee.getFullName();
            reportEmployee.salary = employee.getSalary();
            reportEmployee.registerDate = employee.getRegistrationDate();

            reportEmployees.add(reportEmployee);
            organizations.add(employee.getOffice().getOrganization().getName());
            offices.add(employee.getOffice().getName());
        }

        ReportView reportView = new ReportView();
        reportView.organizationsCount = organizations.size();
        reportView.officesCount = offices.size();
        reportView.employeesCount = employees.size();
        reportView.employees = reportEmployees;

        return reportView;

    }


    private void checkEmployee(Employee employee) {
        String firstName = employee.getFirstName();
        String secondName = employee.getSecondName();
        String middleName = employee.getMiddleName();
        String phone = employee.getPhone();
        String docNumber = employee.getDocNumber();
        Date docDate = employee.getDocDate();

        StringBuilder errorMessage = new StringBuilder(200);

        if((firstName == null) || (!checkName(firstName))){
            errorMessage.append("employeeFirstName is wrong; ");
        }
        if((secondName == null) || (!checkName(secondName))){
            errorMessage.append("employeeSecondName is wrong; ");
        }
        if((middleName != null) && (!checkName(middleName))){
            errorMessage.append("employeeMiddleName is wrong; ");
        }

        if((phone != null) && (!checkPhone(phone))) {
            errorMessage.append("employeePhone is wrong; ");
        }

        if(Strings.isNullOrEmpty(docNumber)){
            errorMessage.append("docNumber is wrong; ");
        }

        if(docDate == null) {
            errorMessage.append("docDate is wrong; ");
        }

        if(errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

    }

    private void checkFilterParams(EmployeeFilter filter) {
        Long officeId = filter.officeId;
        String firstName = filter.firstName;
        String secondName = filter.secondName;
        String middleName = filter.middleName;
        String position = filter.position;
        Integer citizenshipCode = filter.citizenshipCode;

        StringBuilder errorMessage = new StringBuilder(200);

        if((officeId != null) || (!Strings.isNullOrEmpty(firstName)) || (!Strings.isNullOrEmpty(middleName)) || (!Strings.isNullOrEmpty(position)) || (citizenshipCode != null)) {
            officeDao.loadById(officeId);

            if((firstName != null) && (!checkName(firstName))) {
                errorMessage.append("firstName is wrong; ");
            }

            if((secondName != null) && (!checkName(secondName))) {
                errorMessage.append("secondName is wrong; ");
            }

            if((middleName != null) && (!checkName(middleName))) {
                errorMessage.append("middleName is wrong; ");
            }

        }

        if(errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

    }

    private void checkReportFilterParams(ReportFilter filter){

        Date dateFrom = filter.dateFrom;
        Date dateTo = filter.dateTo;
        BigDecimal salaryFrom = filter.salaryFrom;
        BigDecimal salaryTo = filter.salaryTo;

        StringBuilder errorMessage = new StringBuilder(200);

        if(dateFrom == null){
            errorMessage.append("dateFrom is wrong; ");
        }

        if((dateTo == null) || (dateTo.compareTo(dateFrom) < 0)){
            errorMessage.append("dateTo is wrong; ");
        }

        if(salaryFrom == null) {
            errorMessage.append("salaryFrom is wrong; ");
        }

        if((salaryTo == null) || (salaryTo.compareTo(salaryFrom) < 0)){
            errorMessage.append("salaryTo is wrong; ");
        }

        if(errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }



    }


    private boolean checkName(String name) {
         return name.matches("[A-zА-я]+");
    }

    private boolean checkPhone(String phone) {
        return phone.matches("^\\d[\\d\\(\\)\\ -]{8,20}\\d$");
    }

}
