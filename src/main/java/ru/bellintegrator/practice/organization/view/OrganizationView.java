package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {

    public Long id;
    public String name;
    public String fullName;
    public String inn;
    public String kpp;
    public Integer countryCode;
    public String countryName;
    public String address;
    public String phone;
    public Boolean isActive;
    public Integer officesCount;

    public OrganizationView() {
    }

    @Override
    public String toString() {
        return "OrganizationView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
