package ru.bellintegrator.practice.reference.service;

import ru.bellintegrator.practice.reference.model.Country;

import java.util.List;

public interface ExcelDocumentService {

    /**
     *
     * get list of countries from excel file
     *
     * @param fileLocation
     * @return
     */
    List<Country> getCountryList(String fileLocation);
}
