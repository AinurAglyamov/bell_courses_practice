package ru.bellintegrator.practice.office.view;

public class OfficeFilter {

    public Long orgId;
    public String name;
    public String phone;
    public Boolean isActive;

    public OfficeFilter() {
    }

    @Override
    public String toString() {
        return "{" +
                "orgId:" + orgId +
                ", name:'" + name + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
