package ru.bellintegrator.practice.office.view;

public class OfficeToSave {

    public String name;
    public String address;
    public String phone;
    public Boolean isActive;
    public Long orgId;

    public OfficeToSave() {
    }


    public OfficeToSave(String name, String address, String phone, Boolean isActive, Long orgId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
        this.orgId = orgId;
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
