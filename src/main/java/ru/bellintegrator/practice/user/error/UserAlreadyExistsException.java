package ru.bellintegrator.practice.user.error;

public class UserAlreadyExistsException extends UserException {

    public UserAlreadyExistsException(String login) {
        super("Пользователь с логином " + login + " уже существует");
    }
}
