package ru.bellintegrator.practice.organization.dao.impl;

import com.google.common.base.Strings;
import com.google.common.primitives.Booleans;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        Organization organizationToDelete = loadById(id);

        em.remove(organizationToDelete);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> list(Organization organization) {
        String name = organization.getName();
        String inn = organization.getInn();
        Boolean isActive = organization.isActive();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> organizationCriteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = organizationCriteriaQuery.from(Organization.class);

        organizationCriteriaQuery.select(organizationRoot);

        Predicate criteria = builder.conjunction();

        if(!Strings.isNullOrEmpty(name)) {
            Predicate p = builder.equal(organizationRoot.get("name"), name);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(inn)) {
            Predicate p = builder.equal(organizationRoot.get("inn"), inn);
            criteria = builder.and(criteria, p);
        }

        if(isActive != null) {
            Predicate p = builder.equal(organizationRoot.get("isActive"), isActive);
            criteria = builder.and(criteria, p);
        }

        organizationCriteriaQuery.where(criteria);
        List<Organization> organizations = em.createQuery(organizationCriteriaQuery).getResultList();

        return organizations;
    }
}
