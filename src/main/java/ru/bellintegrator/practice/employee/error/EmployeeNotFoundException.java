package ru.bellintegrator.practice.employee.error;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Сотрудник с id = " + id + " не существует");
    }
}
