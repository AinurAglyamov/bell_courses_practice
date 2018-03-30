package ru.bellintegrator.practice.employee.service;

import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import ru.bellintegrator.practice.employee.view.report.ReportView;

import java.util.List;


public interface EmployeeService {

    /**
     *
     * get Employee by id
     *
     * @param id
     * @return JSON employee value
     */
    EmployeeView loadById(Long id);

    /**
     *
     * save Employee
     *
     * @param view
     */
    EmployeeView save(EmployeeToSave view);

    /**
     *
     * update Employee
     *
     * @param view
     *
     */
    void update(EmployeeView view);

    /**
     *
     * delete Employee
     *
     * @param id
     *
     */
    void delete(Long id);

    /**
     * get Employees
     *
     * @param view
     * @return JSON employees value
     */
    List<EmployeeView> list(EmployeeFilter view);

    /**
     *
     * do report on employees
     *
     * @param view
     * @return
     */
    ReportView report(ReportFilter view);
}
