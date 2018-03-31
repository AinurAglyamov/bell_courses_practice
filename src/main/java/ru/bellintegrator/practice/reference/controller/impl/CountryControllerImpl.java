package ru.bellintegrator.practice.reference.controller.impl;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bellintegrator.practice.reference.controller.CountryController;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.service.CountryService;
import ru.bellintegrator.practice.reference.service.ExcelDocumentService;
import ru.bellintegrator.practice.reference.view.CountryView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
@Api(value = "Country controller API")
public class CountryControllerImpl implements CountryController {

    private CountryService countryService;
    private ExcelDocumentService excelDocumentService;

    private final Logger log = LoggerFactory.getLogger(CountryControllerImpl.class);

    private String fileLocation;

    @Autowired
    public CountryControllerImpl(CountryService countryService, ExcelDocumentService excelDocumentService) {
        this.countryService = countryService;
        this.excelDocumentService = excelDocumentService;
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping
    @ApiOperation(value = "get list of countries", httpMethod = "GET")
    @Override
    public List<CountryView> all() {
        return countryService.all();
    }


    /**
     * {@inheritDoc}
     */
    @PostMapping("/upload")
    @ApiOperation(value = "load excel file with list of countries", httpMethod = "POST")
    @Override
    public void uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();

            log.info(path);

            fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();

            log.info(fileLocation);

            byte[] fileBytes = file.getBytes();
            File newFile = new File(fileLocation);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
            stream.write(fileBytes);
            stream.close();
        } catch (Exception e) {
            log.error(null, e);

        }

    }


    /**
     * {@inheritDoc}
     */
    @GetMapping("/update")
    @ApiOperation(value = "load excel file with list of countries", httpMethod = "GET")
    @Override
    public void updateCountriesUsingExcelFile() {
        if((Strings.isNullOrEmpty(fileLocation)) || (!fileLocation.endsWith(".xlsx"))){
            throw new IllegalArgumentException("Файл некорректен");
        }

        List<Country> countries = excelDocumentService.getCountryList(fileLocation);
        countryService.updateCountries(countries);

    }
}
