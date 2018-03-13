package ru.bellintegrator.practice.employee.controller;

import org.springframework.web.bind.annotation.PathVariable;
import ru.bellintegrator.practice.employee.view.EmployeeView;

public interface EmployeeController {

    /**
     *
     * get Employee by id
     *
     * @param id
     * @return JSON employee value
     */
    EmployeeView employeeById(@PathVariable Long id);


}
