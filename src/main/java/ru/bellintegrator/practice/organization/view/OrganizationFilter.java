package ru.bellintegrator.practice.organization.view;

public class OrganizationFilter {

    public String name;
    public String inn;
    public Boolean isActive;

    public OrganizationFilter() {
    }

    public OrganizationFilter(String name, String inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
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
