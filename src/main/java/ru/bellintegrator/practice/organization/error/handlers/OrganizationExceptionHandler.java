package ru.bellintegrator.practice.organization.error.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.organization.error.OrganizationNotFoundException;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class OrganizationExceptionHandler {

    Logger log = LoggerFactory.getLogger(OrganizationExceptionHandler.class);

    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseView organizationNotFoundExceptionHandler(OrganizationNotFoundException e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();

        log.info("mess: " + view.error);

        return view;
    }
}
