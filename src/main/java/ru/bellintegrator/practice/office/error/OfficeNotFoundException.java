package ru.bellintegrator.practice.office.error;

public class OfficeNotFoundException extends RuntimeException {
    public OfficeNotFoundException(Long id){
        super("Офис с id = " + id +" не существует");
    }
}
