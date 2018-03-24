package ru.bellintegrator.practice.employee.view;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeToSave {

    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public BigDecimal salary;
    public Date registrationDate;
    public String phone;
    public Integer docCode;
    public String docNumber;
    public Date docDate;
    public Integer citizenshipCode;
    public Long officeId;

    public EmployeeToSave() {
    }

    @Override
    public String toString() {
        return "EmployeeToSave{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", registrationDate=" + registrationDate +
                ", phone='" + phone + '\'' +
                ", docCode=" + docCode +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipCode=" + citizenshipCode +
                ", officeId=" + officeId +
                '}';
    }
}
