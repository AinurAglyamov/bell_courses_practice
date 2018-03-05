package ru.bellintegrator.practice.organization.view;

public class OrganizationView {

    public Long id;
    public String name;
    public String fullName;
    public String inn;
    public String kpp;
    public String address;
    public String phone;
    public boolean isActive;

    public OrganizationView() {
    }

    public OrganizationView(Long id, String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.id = id;
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
                "id:" + id +
                ", name:'" + name + '\'' +
                ", fullName:'" + fullName + '\'' +
                ", inn:'" + inn + '\'' +
                ", kpp:'" + kpp + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
