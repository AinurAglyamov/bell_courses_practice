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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
@Api(value = "Organization controller API")
public class OrganizationControllerImpl implements OrganizationController{

    private OrganizationService organizationService;

    @Autowired
    public OrganizationControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    @ApiOperation(value = "get Organization by id", httpMethod = "GET")
    @RequestMapping(value = "/{id}", method = {GET})
    public OrganizationView organizationById(@PathVariable Long id) {
        return organizationService.loadById(id);
    }

    @Override
    @ApiOperation(value = "add Organization", httpMethod = "POST")
    @RequestMapping(value = "/save", method = {POST})
    public void saveOrganization(@RequestBody OrganizationView organization) {
        organizationService.save(organization);
    }

    @Override
    @ApiOperation(value = "update Organization", httpMethod = "POST")
    @RequestMapping(value = "/update", method = {POST})
    public void updateOrganization(@RequestBody OrganizationView organization) {
        organizationService.update(organization);
    }

    @Override
    @ApiOperation(value = "delete Organization", httpMethod = "POST")
    @RequestMapping(value = "/delete", method = {POST})
    public void deleteOrganization(@RequestBody Long id) {
        organizationService.delete(id);
    }


    @Override
    @ApiOperation(value = "get Organization list", httpMethod = "POST")
    @RequestMapping(value = "/list", method = {POST})
    public List<OrganizationView> list(@RequestBody OrganizationView view) {
        return organizationService.list(view);
    }
}
