package ru.bellintegrator.practice.employee.view;

public class EmployeeFilter {

    public Long officeId;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public Integer docCode;
    public Integer citizenshipCode;

    public EmployeeFilter() {
    }

    public EmployeeFilter(Long officeId, String firstName, String secondName, String middleName, String position, Integer docCode, Integer citizenshipCode) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    @Override
    public String toString() {
        return "{" +
                "officeId:" + officeId +
                ", firstName:'" + firstName + '\'' +
                ", secondName:'" + secondName + '\'' +
                ", middleName:'" + middleName + '\'' +
                ", position:'" + position + '\'' +
                ", docCode:'" + docCode + '\'' +
                ", citizenshipCode:" + citizenshipCode +
                '}';
    }
}
