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
}
