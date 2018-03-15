package ru.bellintegrator.practice.reference.service;

import ru.bellintegrator.practice.reference.view.DocumentTypeView;

import java.util.List;

public interface DocumentTypeService {

    /**
     *
     * get all document types
     *
     * @return list of document types
     */
    List<DocumentTypeView> all();
}
