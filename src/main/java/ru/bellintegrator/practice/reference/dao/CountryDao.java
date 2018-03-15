package ru.bellintegrator.practice.reference.dao;

import ru.bellintegrator.practice.reference.model.Country;

import java.util.List;

public interface CountryDao {

    /**
     *
     * get all Countries
     *
     * @return list of countries
     */
    List<Country> all();
}
