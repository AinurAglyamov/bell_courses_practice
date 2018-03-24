package ru.bellintegrator.practice.employee.view;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeView {

    public Long id;
    public Long officeId;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public BigDecimal salary;
    public Date registrationDate;
    public String phone;
    public Integer docCode;
    public String docName;
    public String docNumber;
    public Date docDate;
    public Integer citizenshipCode;
    public String citizenshipName;

    public EmployeeView() {
    }

    @Override
    public String toString() {
        return "EmployeeView{" +
                "id=" + id +
                ", officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", registrationDate=" + registrationDate +
                ", phone='" + phone + '\'' +
                ", docCode=" + docCode +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipCode=" + citizenshipCode +
                ", citizenshipName='" + citizenshipName + '\'' +
                '}';
    }
}
