package ru.bellintegrator.practice.user.error;

public class UserIsNotActivatedException extends UserException{

    public UserIsNotActivatedException(String message) {
        super(message);
    }
}
