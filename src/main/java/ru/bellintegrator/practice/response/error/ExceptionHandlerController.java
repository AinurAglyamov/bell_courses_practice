package ru.bellintegrator.practice.response.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.bellintegrator.practice.response.view.ResponseView;

@RestControllerAdvice
public class ExceptionHandlerController {

    Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({Exception.class})
    public ResponseView unhandledException(Exception e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();

        log.info("Сообщеение: " + view.error);

        return view;
    }

    /*@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseView MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = "Передано не число";
        return view;
    }*/
}
