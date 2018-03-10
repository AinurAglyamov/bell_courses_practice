package ru.bellintegrator.practice.organization.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationController {

    /**
     * Get organization by id
     * @param id
     * @return JSON organization value
     */
    OrganizationView organizationById(@PathVariable Long id);

    /**
     * save Organization
     *
     * @param organization
     */
    void saveOrganization(@RequestBody OrganizationView organization);

    /**
     * update Organization
     *
     * @param organization
     */
    void updateOrganization(@RequestBody OrganizationView organization);

    /**
     * delete Organization
     *
     * @param id
     */
    void deleteOrganization(@RequestBody Long id);

    /**
     *
     * get Organization list
     *
     * @param view
     * @return JSON organizations value
     */
    List<OrganizationView> list(OrganizationView view);
}
