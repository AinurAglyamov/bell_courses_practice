package ru.bellintegrator.practice.employee.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;

import java.util.List;

public interface EmployeeController {

    /**
     *
     * get Employee by id
     *
     * @param id
     * @return JSON employee value
     */
    EmployeeView employeeById(@PathVariable Long id);

    /**
     *
     * save Employee
     *
     * @param employee
     */
    void saveEmployee(@RequestBody EmployeeToSave employee);

    /**
     *
     * update Employee
     *
     * @param employee
     */
    void updateEmployee(@RequestBody EmployeeView employee);

    /**
     *
     * delete Employee
     *
     * @param id
     */
    void deleteEmployee(@RequestBody Long id);

    /**
     *
     * get Employee list
     *
     * @param employee
     * @return JSON employees value
     */
    List<EmployeeView> list(@RequestBody EmployeeFilter employee);
}