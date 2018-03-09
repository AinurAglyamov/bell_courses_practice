package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

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
