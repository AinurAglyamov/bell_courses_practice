package ru.bellintegrator.practice.employee.error.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.employee.error.EmployeeNotFoundException;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    Logger log = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseView employeeNotFoundExceptionHandler(EmployeeNotFoundException e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();
        log.info("messs : " + view.error);

        return view;
    }
}
