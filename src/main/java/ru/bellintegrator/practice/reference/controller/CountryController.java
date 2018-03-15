package ru.bellintegrator.practice.reference.controller;

import ru.bellintegrator.practice.reference.model.Country;
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
}
