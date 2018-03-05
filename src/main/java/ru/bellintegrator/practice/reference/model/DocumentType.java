package ru.bellintegrator.practice.reference.model;

import javax.persistence.*;

@Entity
@Table(name = "Document_Type")
public class DocumentType {

    @Id
    @GeneratedValue
    private Long id;

    private Integer code;

    private String name;

    @Version
    private Integer version;

    public DocumentType() {
    }

    public DocumentType(Integer code, String name) {
        this.code = code;
        this.name = name;
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
