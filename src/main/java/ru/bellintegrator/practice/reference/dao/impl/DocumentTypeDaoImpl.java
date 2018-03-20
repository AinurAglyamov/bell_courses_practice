package ru.bellintegrator.practice.reference.dao.impl;

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
        TypedQuery<DocumentType> query = em.createNamedQuery("findByCode", DocumentType.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    @Override
    public DocumentType findByName(String name) {
        TypedQuery<DocumentType> query = em.createNamedQuery("findByName", DocumentType.class);
        query.setParameter("name", name);
        return query.getSingleResult();
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

        DocumentType documentType = null;

        try {
            documentType = query.getSingleResult();
        } catch (NoResultException e) {
            throw new DocumentTypeNotFoundException(code, name);
        }

        return documentType;
    }
}
