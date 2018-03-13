package ru.bellintegrator.practice.reference.model;

import ru.bellintegrator.practice.employee.model.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private Integer code;

    private String name;

    @Version
    private Integer version;

    public Country() {
    }

    public Country(Integer code, String name) {
        this.code = code;
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
