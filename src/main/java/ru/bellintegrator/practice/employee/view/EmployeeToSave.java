package ru.bellintegrator.practice.employee.view;

import java.util.Date;

public class EmployeeToSave {

    public Long id;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public String phone;
    public String docCode;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipName;
    public Integer citizenshipCode;
    public boolean isIdentified;

    public EmployeeToSave() {
    }

    public EmployeeToSave(Long id, String firstName, String secondName, String middleName, String position, String phone, String docCode, String docName, String docNumber, Date docDate, String citizenshipName, Integer citizenshipCode, boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipName = citizenshipName;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", firstName:'" + firstName + '\'' +
                ", secondName:'" + secondName + '\'' +
                ", middleName:'" + middleName + '\'' +
                ", position:'" + position + '\'' +
                ", phone:'" + phone + '\'' +
                ", docCode:'" + docCode + '\'' +
                ", docName:'" + docName + '\'' +
                ", docNumber:'" + docNumber + '\'' +
                ", docDate:" + docDate +
                ", citizenshipName:'" + citizenshipName + '\'' +
                ", citizenshipCode:" + citizenshipCode +
                ", isIdentified:" + isIdentified +
                '}';
    }
}
