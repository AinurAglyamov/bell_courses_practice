package ru.bellintegrator.practice.reference.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Document {

    @Id
    private Long code;

    private String name;

    public Document() {
    }

    public Document(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
