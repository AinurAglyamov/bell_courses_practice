package ru.bellintegrator.practice.organization.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {

        Organization organizationToUpdate = loadById(organization.getId());

        organizationToUpdate.setName(organization.getName());
        organizationToUpdate.setFullName(organization.getFullName());
        organizationToUpdate.setInn(organization.getInn());
        organizationToUpdate.setKpp(organization.getKpp());
        organizationToUpdate.setAddress(organization.getAddress());
        organizationToUpdate.setPhone(organization.getPhone());
        organizationToUpdate.setActive(organization.isActive());

    }

    @Override
    public void delete(Long id) {
        Organization organizationToDelete = loadById(id);

        em.remove(organizationToDelete);
    }
}
