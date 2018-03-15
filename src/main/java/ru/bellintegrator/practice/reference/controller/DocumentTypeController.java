package ru.bellintegrator.practice.reference.controller;

import ru.bellintegrator.practice.reference.view.DocumentTypeView;

import java.util.List;

public interface DocumentTypeController {

    /**
     *
     * get all document types
     *
     * @return list of document types
     */
    List<DocumentTypeView> all();
}
