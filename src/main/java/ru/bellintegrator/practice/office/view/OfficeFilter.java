package ru.bellintegrator.practice.office.view;

public class OfficeFilter {

    public Long orgId;
    public String name;
    public String phone;
    public Boolean isActive;

    public OfficeFilter() {
    }

    public OfficeFilter(Long orgId, String name, String phone, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.isActive = isActive;
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
