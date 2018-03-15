package ru.bellintegrator.practice.reference.controller.impl;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.reference.controller.DocumentTypeController;
import ru.bellintegrator.practice.reference.service.DocumentTypeService;
import ru.bellintegrator.practice.reference.view.DocumentTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
@Api(value = "Document types controller API")
public class DocumentTypeControllerImpl implements DocumentTypeController{

    private DocumentTypeService service;

    @Autowired
    public DocumentTypeControllerImpl(DocumentTypeService service) {
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping
    @ApiOperation(value = "get list of document types", httpMethod = "GET")
    @Override
    public List<DocumentTypeView> all() {
        return service.all();
    }
}
