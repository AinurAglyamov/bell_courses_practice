package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationService {

    /**
     *
     * @param id
     * @return JSON organization value
     */
    OrganizationView loadById(Long id);
}
