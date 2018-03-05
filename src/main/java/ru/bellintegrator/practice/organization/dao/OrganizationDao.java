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
}
