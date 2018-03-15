package ru.bellintegrator.practice.reference.view;

public class DocumentTypeView {

    public String name;
    public Integer code;

    public DocumentTypeView() {
    }

    public DocumentTypeView(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", code:" + code +
                '}';
    }
}
