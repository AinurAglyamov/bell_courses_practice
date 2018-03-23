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

    public OrganizationToSave(String name, String fullName, String inn, String kpp, Integer countryCode, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.countryCode = countryCode;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
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
