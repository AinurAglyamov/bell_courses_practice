package ru.bellintegrator.practice.user.error.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.response.view.ResponseView;
import ru.bellintegrator.practice.user.error.UserAlreadyExistsException;

@RestControllerAdvice
public class UserExceptionHandler {

    Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseView userAlreadyExistsExceptionHandler(UserAlreadyExistsException e){
        log.error(null, e);

        ResponseView view = new ResponseView();
        view.error = e.getMessage();
        log.info("res: " + view.error);
        return view;
    }

}
