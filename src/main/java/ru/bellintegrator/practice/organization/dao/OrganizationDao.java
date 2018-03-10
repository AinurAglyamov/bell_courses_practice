package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

/**
 *
 * DAO для работы с Organization
 *
 */
public interface OrganizationDao {

    /**
     *
     * get Organization by id
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     *
     * get Organizations
     *
     * @param organization
     * @return
     */
    List<Organization> list(Organization organization);

    /**
     * add Organization
     *
     * @param organization
     *
     *
     */
    void save(Organization organization);

    /**
     * update Organization
     *
     * @param organization
     */
    void update(Organization organization);

    /**
     * delete Organization
     *
     * @param id
     */
    void delete(Long id);
}
