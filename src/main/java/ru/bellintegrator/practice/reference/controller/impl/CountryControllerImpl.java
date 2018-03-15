package ru.bellintegrator.practice.reference.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.reference.controller.CountryController;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.service.CountryService;
import ru.bellintegrator.practice.reference.view.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
@Api(value = "Country controller API")
public class CountryControllerImpl implements CountryController {

    private CountryService service;

    @Autowired
    public CountryControllerImpl(CountryService service) {
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping
    @ApiOperation(value = "get list of countries", httpMethod = "GET")
    @Override
    public List<CountryView> all() {
        return service.all();
    }
}
