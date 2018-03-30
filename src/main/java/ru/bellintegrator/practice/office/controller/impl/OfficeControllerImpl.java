package ru.bellintegrator.practice.office.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.controller.OfficeController;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @GetMapping("/{id}")
    public OfficeView officeById(@PathVariable Long id) {
        return officeService.loadById(id);
    }

    @Override
    @ApiOperation(value = "create Office", httpMethod = "POST")
    @PostMapping("/create")
    public OfficeView createOffice(@RequestBody OfficeToSave office) {
        return officeService.save(office);
    }

    @Override
    @ApiOperation(value = "update Office", httpMethod = "POST")
    @PostMapping("/update")
    public void updateOffice(@RequestBody OfficeView office) {
        officeService.update(office);
    }

    @Override
    @ApiOperation(value = "delete Office", httpMethod = "GET")
    @GetMapping("/delete/{id}")
    public void deleteOffice(@PathVariable Long id) {
        officeService.delete(id);
    }

    @Override
    @ApiOperation(value = "get Office list", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeView> list(@RequestBody OfficeFilter office) {
        return officeService.list(office);
    }
}
