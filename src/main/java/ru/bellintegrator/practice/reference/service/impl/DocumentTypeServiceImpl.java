package ru.bellintegrator.practice.reference.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.reference.dao.DocumentTypeDao;
import ru.bellintegrator.practice.reference.model.DocumentType;
import ru.bellintegrator.practice.reference.service.DocumentTypeService;
import ru.bellintegrator.practice.reference.view.DocumentTypeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private DocumentTypeDao dao;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    @Transactional
    public List<DocumentTypeView> all() {
        List<DocumentType> documentTypes = dao.all();

        Function<DocumentType, DocumentTypeView> mapDocumentType = d -> {
            return new DocumentTypeView(d.getName(),d.getCode());
        };

        return documentTypes.stream().map(mapDocumentType).collect(Collectors.toList());
    }
}
