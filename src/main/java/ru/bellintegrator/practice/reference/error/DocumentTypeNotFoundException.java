package ru.bellintegrator.practice.reference.error;

public class DocumentTypeNotFoundException extends ReferenceException{

    public DocumentTypeNotFoundException(Integer code, String name) {
        super("DocumentType with code = " + code + " and with name = " + name + " not found");

    }
}
