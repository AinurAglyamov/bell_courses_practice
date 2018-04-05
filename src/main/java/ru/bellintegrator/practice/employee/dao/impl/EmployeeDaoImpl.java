package ru.bellintegrator.practice.employee.dao.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.dao.EmployeeDao;
import ru.bellintegrator.practice.employee.error.EmployeeNotFoundException;
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManager em;

    @Autowired
    public EmployeeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee loadById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Employee employee = em.find(Employee.class, id);

        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }

        return employee ;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Employee employee) {
        em.persist(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Employee employee) {
        Employee employeeToUpdate = loadById(employee.getId());

        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setSecondName(employee.getSecondName());
        employeeToUpdate.setMiddleName(employee.getMiddleName());
        employeeToUpdate.setPosition(employee.getPosition());
        employeeToUpdate.setSalary(employee.getSalary());
        employeeToUpdate.setRegistrationDate(employee.getRegistrationDate());
        employeeToUpdate.setPhone(employee.getPhone());
        employeeToUpdate.setDocumentType(employee.getDocumentType());
        employeeToUpdate.setDocNumber(employee.getDocNumber());
        employeeToUpdate.setDocDate(employee.getDocDate());
        employeeToUpdate.setCountry(employee.getCountry());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        Employee employeeToDelete = loadById(id);

        employeeToDelete.getOffice().removeEmployee(employeeToDelete);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> list(Employee employee) {
        Long officeId = employee.getOffice().getId();
        String firstName = employee.getFirstName();
        String secondName = employee.getSecondName();
        String middleName = employee.getMiddleName();
        String position = employee.getPosition();
        Integer citizenshipCode = employee.getCountry().getCode();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeCriteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);

        employeeCriteriaQuery.select(employeeRoot);

        Predicate criteria = builder.conjunction();

        if(officeId != null) {
            Predicate p = builder.equal(employeeRoot.get("office").get("id"), officeId);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(firstName)) {
            Predicate p = builder.equal(employeeRoot.get("firstName"), firstName);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(secondName)) {
            Predicate p = builder.equal(employeeRoot.get("secondName"), secondName);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(middleName)) {
            Predicate p = builder.equal(employeeRoot.get("middleName"), middleName);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(position)) {
            Predicate p = builder.equal(employeeRoot.get("position"), position);
            criteria = builder.and(criteria, p);
        }

        if(citizenshipCode != null) {
            Predicate p = builder.equal(employeeRoot.get("country").get("code"), citizenshipCode);
            criteria = builder.and(criteria, p);
        }

        employeeCriteriaQuery.where(criteria);
        List<Employee> employees = em.createQuery(employeeCriteriaQuery).getResultList();

        return employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> loadBySalaryAndRegDate(ReportFilter filter){
        BigDecimal salaryFrom = filter.salaryFrom;
        BigDecimal salaryTo = filter.salaryTo;
        Date dateFrom = filter.dateFrom;
        Date dateTo = filter.dateTo;

        return em.createQuery("SELECT e FROM Employee e WHERE e.salary BETWEEN :salaryFrom AND :salaryTo AND e.registrationDate BETWEEN :dateFrom AND :dateTo", Employee.class)
                .setParameter("salaryFrom", salaryFrom)
                .setParameter("salaryTo", salaryTo)
                .setParameter("dateFrom", dateFrom, TemporalType.DATE)
                .setParameter("dateTo", dateTo, TemporalType.DATE)
                .getResultList();

    }
}
