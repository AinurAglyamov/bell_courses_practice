package ru.bellintegrator.practice.organization.view;

public class OrganizationFilter {

    public String name;
    public String inn;
    public boolean isActive;

    public OrganizationFilter() {
    }

    public OrganizationFilter(String name, String inn, boolean isActive) {
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
