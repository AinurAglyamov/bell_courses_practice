package ru.bellintegrator.practice.office.view;

public class OfficeToSave {

    public String name;
    public String address;
    public String phone;
    public Boolean isActive;
    public Long orgId;

    public OfficeToSave() {
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                ", orgId:" + orgId +
                '}';
    }
}
