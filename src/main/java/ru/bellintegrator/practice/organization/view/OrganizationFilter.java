package ru.bellintegrator.practice.organization.view;

public class OrganizationFilter {

    public String name;
    public String inn;
    public Boolean isActive;

    public OrganizationFilter() {
    }


    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", inn:'" + inn + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
