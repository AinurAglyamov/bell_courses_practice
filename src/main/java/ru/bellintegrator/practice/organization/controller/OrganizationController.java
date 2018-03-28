package ru.bellintegrator.practice.organization.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.organization.view.OrganizationFilter;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationController {

    /**
     * Get organization by id
     * @param id organization id
     * @return JSON organization value
     */
    OrganizationView organizationById(@PathVariable Long id);

    /**
     *
     * create Organization
     *
     * @param organization organization need to be saved
     * @return JSON organization value
     */
    OrganizationView createOrganization(@RequestBody OrganizationToSave organization);

    /**
     * update Organization
     *
     * @param organization organization need to be updated
     */
    void updateOrganization(@RequestBody OrganizationView organization);

    /**
     * delete Organization
     *
     * @param id organization id which need to be deleted
     */
    void deleteOrganization(@PathVariable Long id);

    /**
     *
     * get Organization list
     *
     * @param view
     * @return JSON organizations value
     */
    List<OrganizationView> list(OrganizationFilter view);
}
