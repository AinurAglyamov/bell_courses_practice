package ru.bellintegrator.practice.user.error;

public class UserAlreadyExistsException extends RuntimeException {

    /*public UserAlreadyExistsException(String message) {
        super(message);
    }*/

    public UserAlreadyExistsException(String login) {
        super("Пользователь с логином " + login + " уже существует");
    }/**/
}
