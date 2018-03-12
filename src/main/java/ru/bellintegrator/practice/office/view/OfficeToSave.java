package ru.bellintegrator.practice.office.view;

public class OfficeToSave {

    public String name;
    public String address;
    public String phone;
    public boolean isActive;

    public OfficeToSave() {
    }

    public OfficeToSave(String name, String address, String phone, boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
