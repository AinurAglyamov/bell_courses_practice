package ru.bellintegrator.practice.office.error.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.office.error.OfficeNotFoundException;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class OfficeExceptionHandler {

    Logger log  = LoggerFactory.getLogger(OfficeExceptionHandler.class);

    @ExceptionHandler(OfficeNotFoundException.class)
    public ResponseView officeNotFoundExceptionHandler(OfficeNotFoundException e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();

        log.info("mess : " + view.error);

        return view;
    }
}
