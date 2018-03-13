package ru.bellintegrator.practice.employee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.employee.model.Employee;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;


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
    void save(EmployeeToSave view);
}
