package ru.bellintegrator.practice.reference.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.reference.dao.DocumentTypeDao;
import ru.bellintegrator.practice.reference.error.DocumentTypeNotFoundException;
import ru.bellintegrator.practice.reference.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {

    private EntityManager em;

    @Autowired
    public DocumentTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentType> all() {
        TypedQuery<DocumentType> query = em.createQuery("SELECT d FROM DocumentType d", DocumentType.class);
        return query.getResultList();
    }

    @Override
    public DocumentType findByCode(Integer code) {
        TypedQuery<DocumentType> query = em.createNamedQuery("findDocTypeByCode", DocumentType.class);
        query.setParameter("code", code);

        List<DocumentType> documentTypes = query.getResultList();

        if(documentTypes.isEmpty()){
            throw new DocumentTypeNotFoundException("document type with code = " + code + " is not exists");
        }

        return documentTypes.get(0);
    }

    @Override
    public DocumentType findByName(String name) {
        TypedQuery<DocumentType> query = em.createNamedQuery("findDocTypeByName", DocumentType.class);
        query.setParameter("name", name);

        List<DocumentType> documentTypes = query.getResultList();

        if(documentTypes.isEmpty()){
            throw new DocumentTypeNotFoundException("document type with name = " + name + " is not exists");
        }

        return documentTypes.get(0);
    }

    @Override
    public DocumentType findByNameAndCode(Integer code, String name) {
        if(code == null) {
            throw new IllegalArgumentException("docCode is null");
        }

        if(name == null) {
            throw new IllegalArgumentException("docName is null");
        }

        TypedQuery<DocumentType> query = em.createNamedQuery("findDocTypeByCodeAndName", DocumentType.class);

        query.setParameter("code", code);
        query.setParameter("name", name);

        List<DocumentType> documentTypes = query.getResultList();

        if(documentTypes.isEmpty()){
            throw new DocumentTypeNotFoundException("document type with code = " + name + " and name = " + name + " is not exists");
        }

        return documentTypes.get(0);
    }
}
