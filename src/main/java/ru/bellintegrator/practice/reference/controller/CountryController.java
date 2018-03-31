package ru.bellintegrator.practice.reference.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.bellintegrator.practice.reference.view.CountryView;

import java.util.List;

public interface CountryController {

    /**
     *
     * get all countries
     *
     * @return list of countries
     */
    List<CountryView> all();

    /**
     *
     * upload File
     *
     * @param file
     */
    void uploadFile(MultipartFile file);

    /**
     *
     * update countries
     *
      */
    void updateCountriesUsingExcelFile();
}
