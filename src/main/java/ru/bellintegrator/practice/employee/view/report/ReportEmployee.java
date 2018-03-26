package ru.bellintegrator.practice.employee.view.report;

import java.math.BigDecimal;
import java.util.Date;

public class ReportEmployee {

    public String orgName;
    public String officeName;
    public String employeeName;
    public BigDecimal salary;
    public Date registerDate;

    public ReportEmployee() {
    }

    @Override
    public String toString() {
        return "ReportEmployee{" +
                "orgName='" + orgName + '\'' +
                ", officeName='" + officeName + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", registerDate=" + registerDate +
                '}';
    }
}
