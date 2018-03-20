package ru.bellintegrator.practice.reference.dao;

import ru.bellintegrator.practice.reference.model.DocumentType;

import java.util.List;

public interface DocumentTypeDao {

    /**
     *
     * get all document types
     *
     * @return list of document types
     */
    List<DocumentType> all();

    /**
     *
     * get Document type by code
     *
     * @return document type value
     */
    DocumentType findByCode(Integer code);


    /**
     *
     * get Document type by name
     *
     * @return document type value
     */
    DocumentType findByName(String name);

    /**
     *
     * get Document type by code and name
     *
     * @return document type value
     */
    DocumentType findByNameAndCode(Integer code, String name);
}
