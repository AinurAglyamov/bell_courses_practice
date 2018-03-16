package ru.bellintegrator.practice.employee.view;

import java.util.Date;

public class EmployeeToSave {

    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public String phone;
    public Integer docCode;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipName;
    public Integer citizenshipCode;
    public Boolean isIdentified;
    public Long officeId;

    public EmployeeToSave() {
    }


    @Override
    public String toString() {
        return "{" +
                "firstName:'" + firstName + '\'' +
                ", secondName:'" + secondName + '\'' +
                ", middleName:'" + middleName + '\'' +
                ", position:'" + position + '\'' +
                ", phone:'" + phone + '\'' +
                ", docCode:" + docCode +
                ", docName:'" + docName + '\'' +
                ", docNumber:'" + docNumber + '\'' +
                ", docDate:" + docDate +
                ", citizenshipName:'" + citizenshipName + '\'' +
                ", citizenshipCode:" + citizenshipCode +
                ", isIdentified:" + isIdentified +
                ", officeId:" + officeId +
                '}';
    }
}
