package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {

    /**
     *
     * get Organization by id
     *
     * @param id
     * @return JSON organization value
     */
    OrganizationView loadById(Long id);

    /**
     *
     * save Organization
     *
     * @param view
     *
     */
    void save(OrganizationView view);

    /**
     *
     * update Organization
     *
     * @param view
     *
     */
    void update(OrganizationView view);

    /**
     *
     * delete Organization
     *
     * @param id
     *
     */
    void delete(Long id);

    /**
     * get Organizations
     *
     * @param view
     * @return JSON organizations value
     */
    List<OrganizationView> list(OrganizationView view);
}
