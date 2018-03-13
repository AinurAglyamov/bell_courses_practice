package ru.bellintegrator.practice.employee.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeView {

    public Long id;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public String phone;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipName;
    public Integer citizenshipCode;
    public boolean isIdentified;

    public EmployeeView() {
    }

    public EmployeeView(Long id, String firstName, String secondName, String middleName, String position, String phone, String docName, String docNumber, Date docDate, String citizenshipName, Integer citizenshipCode, boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
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
                ", docName:'" + docName + '\'' +
                ", docNumber:'" + docNumber + '\'' +
                ", docDate:" + docDate +
                ", citizenshipName:'" + citizenshipName + '\'' +
                ", citizenshipCode:" + citizenshipCode +
                ", isIdentified:" + isIdentified +
                '}';
    }
}
