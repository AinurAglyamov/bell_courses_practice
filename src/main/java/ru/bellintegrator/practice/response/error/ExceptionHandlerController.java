package ru.bellintegrator.practice.response.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class ExceptionHandlerController {

    Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseView unhandledException(IllegalArgumentException e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();

        log.info("Сообщеение: " + view.error);

        return view;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseView badRequestHandler(HttpMessageNotReadableException e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();
        return view;
    }
}
