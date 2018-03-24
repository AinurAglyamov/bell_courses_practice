package ru.bellintegrator.practice.employee.view;

public class EmployeeFilter {

    public Long officeId;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public Integer citizenshipCode;

    public EmployeeFilter() {
    }

    @Override
    public String toString() {
        return "EmployeeFilter{" +
                "officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", citizenshipCode=" + citizenshipCode +
                '}';
    }
}
