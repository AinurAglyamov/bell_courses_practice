package ru.bellintegrator.practice.organization.controller.impl;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.controller.OrganizationController;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationFilter;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
@Api(value = "Organization controller API")
public class OrganizationControllerImpl implements OrganizationController{

    private OrganizationService organizationService;

    @Autowired
    public OrganizationControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "get Organization by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationView organizationById(@PathVariable Long id) {
        return organizationService.loadById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "create Organization", httpMethod = "POST")
    @PostMapping("/create")
    public OrganizationView createOrganization(@RequestBody OrganizationToSave organization) {
        return organizationService.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "update Organization", httpMethod = "POST")
    @PostMapping("/update")
    public void updateOrganization(@RequestBody OrganizationView organization) {
        organizationService.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "delete Organization", httpMethod = "GET")
    @GetMapping("/delete/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.delete(id);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "get Organization list", httpMethod = "POST")
    @PostMapping("/list")
    public List<OrganizationView> list(@RequestBody OrganizationFilter view) {
        return organizationService.list(view);
    }
}
