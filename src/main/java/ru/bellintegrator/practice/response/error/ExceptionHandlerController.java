package ru.bellintegrator.practice.response.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class ExceptionHandlerController {

    Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Exception.class)
    public ResponseView unhandledException(Exception e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();
        return view;
    }
}
