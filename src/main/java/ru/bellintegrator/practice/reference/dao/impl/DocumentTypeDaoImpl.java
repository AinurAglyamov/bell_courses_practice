package ru.bellintegrator.practice.reference.dao.impl;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.reference.dao.DocumentTypeDao;
import ru.bellintegrator.practice.reference.model.DocumentType;

import javax.persistence.EntityManager;
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
}
