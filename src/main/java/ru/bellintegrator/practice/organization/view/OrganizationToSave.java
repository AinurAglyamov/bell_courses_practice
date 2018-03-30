package ru.bellintegrator.practice.organization.view;

public class OrganizationToSave {

    public String name;
    public String fullName;
    public String inn;
    public String kpp;
    public Integer countryCode;
    public String address;
    public String phone;
    public Boolean isActive;

    public OrganizationToSave() {
    }

    @Override
    public String toString() {
        return "OrganizationToSave{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", countryCode=" + countryCode +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
