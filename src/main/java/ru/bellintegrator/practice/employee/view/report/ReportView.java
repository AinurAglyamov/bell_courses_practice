package ru.bellintegrator.practice.employee.view.report;

import java.util.List;

public class ReportView {

    public Integer organizationsCount;
    public Integer officesCount;
    public Integer employeesCount;
    public List<ReportEmployee> employees;

    public ReportView() {
    }

    @Override
    public String toString() {
        return "ReportView{" +
                "organizationsCount=" + organizationsCount +
                ", officesCount=" + officesCount +
                ", employeesCount=" + employeesCount +
                ", employees=" + employees +
                '}';
    }
}
