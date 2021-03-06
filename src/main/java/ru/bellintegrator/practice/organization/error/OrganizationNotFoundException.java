package ru.bellintegrator.practice.organization.error;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException(String message) {
        super(message);
    }

    public OrganizationNotFoundException(Long id) {
        super("Организация с id = " + id + " не существует");
    }

}
