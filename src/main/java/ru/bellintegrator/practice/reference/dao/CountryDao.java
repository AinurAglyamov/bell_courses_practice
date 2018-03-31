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

    /**
     *
     * get Country by code and name
     *
     * @param code
     * @param name
     * @return Country value
     */
    Country findByCodeAndName(Integer code, String name);

    /**
     *
     * get Country by code
     *
     * @param code
     * @return Country value
     */
    Country findByCode(Integer code);


    /**
     *
     * update Countries
     *
     * @param countries
     */
    void updateCountries(List<Country> countries);
}
