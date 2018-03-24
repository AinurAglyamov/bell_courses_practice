package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.employee.model.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Office {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String phone;
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @Version
    private Integer version;

    public Office() {
    }

    public Office(String name, String address, String phone, Boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        if(organization == null){
            organization = new Organization();
        }
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Employee> getEmployees() {
        if(employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        getEmployees().add(employee);
        employee.setOffice(this);
    }

    public void removeEmployee(Employee employee) {
        getEmployees().remove(employee);
        employee.setOffice(null);
    }
}
