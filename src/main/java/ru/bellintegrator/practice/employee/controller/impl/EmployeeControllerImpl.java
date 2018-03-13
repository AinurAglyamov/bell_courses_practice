package ru.bellintegrator.practice.employee.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.employee.controller.EmployeeController;
import ru.bellintegrator.practice.employee.service.EmployeeService;
import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/employee", produces = APPLICATION_JSON_VALUE)
@Api(value = "Employee controller API")
public class EmployeeControllerImpl implements EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "get Employee by id", httpMethod = "GET")
    @RequestMapping(value = "/{id}", method = {GET})
    public EmployeeView employeeById(@PathVariable Long id) {
        return employeeService.loadById(id);
    }

    @Override
    @ApiOperation(value = "save Employee", httpMethod = "POST")
    @RequestMapping(value = "/save", method = {POST})
    public void saveEmployee(@RequestBody EmployeeToSave employee) {
        employeeService.save(employee);
    }

    @Override
    @ApiOperation(value = "update Employee", httpMethod = "POST")
    @RequestMapping(value = "/update", method = {POST})
    public void updateEmployee(@RequestBody EmployeeView employee) {
        employeeService.update(employee);
    }

    @Override
    @ApiOperation(value = "delete Employee", httpMethod = "POST")
    @RequestMapping(value = "/delete", method = {POST})
    public void deleteEmployee(@RequestBody Long id) {
        employeeService.delete(id);
    }

    @Override
    @ApiOperation(value = "get Employee list", httpMethod = "POST")
    @RequestMapping(value = "/list", method = {POST})
    public List<EmployeeView> list(@RequestBody EmployeeFilter employee) {
        return employeeService.list(employee);
    }
}
