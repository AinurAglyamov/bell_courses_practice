package ru.bellintegrator.practice.employee.dao;

import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;

import java.util.List;

public interface EmployeeDao {

    /**
     *
     * get Employee by id
     *
     * @param id
     * @return
     */
    Employee loadById(Long id);

    /**
     *
     * save Employee
     *
     * @param employee
     */
    void save(Employee employee);

    /**
     *
     * update Employee
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     *
     * delete Employee
     *
     * @param id
     */
    void delete(Long id);

    /**
     *
     * get Employees
     *
     * @param employee
     * @return Employee list
     */
    List<Employee> list(Employee employee);

    /**
     *
     * get Employee list by salary and registration date
     *
     * @param filter
     * @return Employee list
     */
    List<Employee> loadBySalaryAndRegDate(ReportFilter filter);
}
