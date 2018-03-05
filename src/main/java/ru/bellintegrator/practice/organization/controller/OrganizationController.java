package ru.bellintegrator.practice.organization.controller;


import org.springframework.web.bind.annotation.PathVariable;
import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationController {

    /**
     * Get organization by id
     * @param id
     * @return JSON organization value
     */
    OrganizationView organizationById(@PathVariable Long id);
}
