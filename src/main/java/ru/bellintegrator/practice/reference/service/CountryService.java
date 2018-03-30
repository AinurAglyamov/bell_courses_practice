package ru.bellintegrator.practice.reference.service;


import ru.bellintegrator.practice.reference.view.CountryView;

import java.util.List;

public interface CountryService {

    /**
     *
     * get all Countries
     *
     * @return list of countries
     */
    List<CountryView> all();
}
