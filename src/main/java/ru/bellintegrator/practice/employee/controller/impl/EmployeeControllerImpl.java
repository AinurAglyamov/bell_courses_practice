package ru.bellintegrator.practice.employee.controller.impl;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.controller.EmployeeController;
import ru.bellintegrator.practice.employee.service.EmployeeService;
import ru.bellintegrator.practice.employee.view.EmployeeFilter;
import ru.bellintegrator.practice.employee.view.EmployeeToSave;
import ru.bellintegrator.practice.employee.view.EmployeeView;
import ru.bellintegrator.practice.employee.view.report.ReportFilter;
import ru.bellintegrator.practice.employee.view.report.ReportView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @GetMapping("/{id}")
    public EmployeeView employeeById(@PathVariable Long id) {
        return employeeService.loadById(id);
    }

    @Override
    @ApiOperation(value = "create Employee", httpMethod = "POST")
    @PostMapping("/create")
    public EmployeeView createEmployee(@RequestBody EmployeeToSave employee) {
        return employeeService.save(employee);
    }

    @Override
    @ApiOperation(value = "update Employee", httpMethod = "POST")
    @PostMapping("/update")
    public void updateEmployee(@RequestBody EmployeeView employee) {
        employeeService.update(employee);
    }

    @Override
    @ApiOperation(value = "delete Employee", httpMethod = "POST")
    @PostMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @Override
    @ApiOperation(value = "get Employee list", httpMethod = "POST")
    @PostMapping("/list")
    public List<EmployeeView> list(@RequestBody EmployeeFilter employee) {
        return employeeService.list(employee);
    }

    @Override
    @ApiOperation(value = "do report", httpMethod = "POST")
    @PostMapping("/report")
    public ReportView report(@RequestBody ReportFilter filter) {
        return employeeService.report(filter);
    }
}
