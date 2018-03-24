package ru.bellintegrator.practice.office.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

//@JsonRootName(value = "data")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {

    public Long id;
    public Long orgId;
    public String name;
    public String address;
    public String phone;
    public Boolean isActive;
    public String organizationName;

    public OfficeView() {
    }

    @Override
    public String toString() {
        return "OfficeView{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                ", organizationName='" + organizationName + '\'' +
                '}';
    }
}
