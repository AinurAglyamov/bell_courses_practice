package ru.bellintegrator.practice.reference.error.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.reference.error.ReferenceException;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class ReferenceExceptionHandler {

    private Logger log = LoggerFactory.getLogger(ReferenceExceptionHandler.class);

    @ExceptionHandler(ReferenceException.class)
    public ResponseView referenceExceptionHandler(ReferenceException e){
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();
        log.info(view.error);

        return view;
    }

}
