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
    public Integer docCode;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipName;
    public Integer citizenshipCode;
    public Boolean isIdentified;

    public EmployeeView() {
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
