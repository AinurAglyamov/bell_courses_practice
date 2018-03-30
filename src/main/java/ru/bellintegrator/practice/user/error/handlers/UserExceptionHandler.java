package ru.bellintegrator.practice.user.error.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.response.view.ResponseView;
import ru.bellintegrator.practice.user.error.UserException;

@RestControllerAdvice
public class UserExceptionHandler {

    Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserException.class)
    public ResponseView userExceptionHandler(UserException e){
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();
        log.info("res: " + view.error);
        return view;
    }

}
