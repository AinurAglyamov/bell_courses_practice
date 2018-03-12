package ru.bellintegrator.practice.organization.view;

public class OrganizationToSave {

    public String name;
    public String fullName;
    public String inn;
    public String kpp;
    public String address;
    public String phone;
    public boolean isActive;

    public OrganizationToSave() {
    }

    public OrganizationToSave(String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", fullName:'" + fullName + '\'' +
                ", inn:'" + inn + '\'' +
                ", kpp:'" + kpp + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
