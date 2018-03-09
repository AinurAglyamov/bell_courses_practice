package ru.bellintegrator.practice.organization.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.controller.OrganizationController;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@Api(value = "Organization controller API")
public class OrganizationControllerImpl implements OrganizationController{

    private OrganizationService organizationService;

    @Autowired
    public OrganizationControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    @ApiOperation(value = "getOrganizationById", nickname = "getOrganizationById", httpMethod = "GET")
    @RequestMapping(value = "/organization/{id}", method = {GET})
    public OrganizationView organizationById(@PathVariable Long id) {
        return organizationService.loadById(id);
    }

    @Override
    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @RequestMapping(value = "/organization/save", method = {POST})
    public void saveOrganization(@RequestBody OrganizationView organization) {
        organizationService.save(organization);
    }

    @Override
    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @RequestMapping(value = "/organization/update", method = {POST})
    public void updateOrganization(@RequestBody OrganizationView organization) {
        organizationService.update(organization);
    }

    @Override
    @ApiOperation(value = "deleteOrganization", nickname = "deleteOrganization", httpMethod = "POST")
    @RequestMapping(value = "/organization/delete", method = {POST})
    public void deleteOrganization(@RequestBody Long id) {
        organizationService.delete(id);
    }
}
