package ru.bellintegrator.practice.office.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.office.controller.OfficeController;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
@Api(value = "Office controller API")
public class OfficeControllerImpl implements OfficeController{

    private OfficeService officeService;

    @Autowired
    public void setOfficeService(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    @ApiOperation(value = "get Office by id", httpMethod = "GET")
    @RequestMapping(value = "/{id}", method = {GET})
    public OfficeView officeById(@PathVariable Long id) {
        return officeService.loadById(id);
    }

    @Override
    @ApiOperation(value = "add Office", httpMethod = "POST")
    @RequestMapping(value = "/save", method = {POST})
    public void saveOffice(@RequestBody OfficeToSave office) {
        officeService.save(office);
    }

    @Override
    @ApiOperation(value = "update Office", httpMethod = "POST")
    @RequestMapping(value = "/update", method = {POST})
    public void updateOffice(@RequestBody OfficeView office) {
        officeService.update(office);
    }

    @Override
    @ApiOperation(value = "delete Office", httpMethod = "POST")
    @RequestMapping(value = "/delete", method = {POST})
    public void deleteOffice(@RequestBody Long id) {
        officeService.delete(id);
    }

    @Override
    @ApiOperation(value = "get Office list", httpMethod = "POST")
    @RequestMapping(value = "/list", method = {POST})
    public List<OfficeView> list(@RequestBody OfficeFilter office) {
        return officeService.list(office);
    }
}
