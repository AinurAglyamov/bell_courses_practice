package ru.bellintegrator.practice.employee.view.report;

import java.math.BigDecimal;
import java.util.Date;

public class ReportFilter {

    public BigDecimal salaryFrom;
    public BigDecimal salaryTo;
    public Date dateFrom;
    public Date dateTo;

    public ReportFilter() {
    }

    @Override
    public String toString() {
        return "ReportFilter{" +
                "salaryFrom=" + salaryFrom +
                ", salaryTo=" + salaryTo +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
