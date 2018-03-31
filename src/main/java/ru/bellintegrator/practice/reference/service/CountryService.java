package ru.bellintegrator.practice.reference.service;


import ru.bellintegrator.practice.reference.model.Country;
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

    /**
     *
     * update Countries
     *
     * @param countries
     */
    void updateCountries(List<Country> countries);
}
