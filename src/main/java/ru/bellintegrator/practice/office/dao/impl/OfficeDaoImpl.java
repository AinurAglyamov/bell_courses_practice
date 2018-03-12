package ru.bellintegrator.practice.office.dao.impl;

import com.google.common.base.Strings;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
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
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        Office officeToUpdate = loadById(office.getId());

        officeToUpdate.setId(office.getId());
        officeToUpdate.setName(office.getName());
        officeToUpdate.setAddress(office.getAddress());
        officeToUpdate.setPhone(office.getPhone());
        officeToUpdate.setActive(office.isActive());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        Office officeToDelete = loadById(id);

        em.remove(officeToDelete);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(Office office) {
        //Organization organization = office.getOrganization();
        Long orgId = office.getOrganization().getId();
        String name = office.getName();
        String phone = office.getPhone();
        boolean isActive = office.isActive();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> officeCriteriaQuery = builder.createQuery(Office.class);
        Root<Office> officeRoot = officeCriteriaQuery.from(Office.class);

        officeCriteriaQuery.select(officeRoot);

        Predicate criteria = builder.conjunction();

        if(orgId != null) {
            Predicate p = builder.equal(officeRoot.get("organization").get("id"), orgId);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(name)) {
            Predicate p = builder.equal(officeRoot.get("name"), name);
            criteria = builder.and(criteria, p);
        }

        if(!Strings.isNullOrEmpty(phone)) {
            Predicate p = builder.equal(officeRoot.get("phone"), phone);
            criteria = builder.and(criteria, p);
        }

        Predicate p = builder.equal(officeRoot.get("isActive"), isActive);
        criteria = builder.and(criteria, p);

        officeCriteriaQuery.where(criteria);
        List<Office> offices = em.createQuery(officeCriteriaQuery).getResultList();

        return offices;
    }
}
