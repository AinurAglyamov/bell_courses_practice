package ru.bellintegrator.practice.office.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {

    public Long id;
    public String name;
    public String address;
    public String phone;
    public Boolean isActive;

    public OfficeView() {
    }

    public OfficeView(Long id, String name, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
