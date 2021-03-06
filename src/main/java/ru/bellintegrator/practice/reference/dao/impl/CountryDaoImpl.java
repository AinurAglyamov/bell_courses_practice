package ru.bellintegrator.practice.reference.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.reference.dao.CountryDao;
import ru.bellintegrator.practice.reference.error.CountryNotFoundException;
import ru.bellintegrator.practice.reference.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    private EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> all() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country findByCodeAndName(Integer code, String name) {
        if(code == null) {
            throw new IllegalArgumentException("citizenshipCode is null");
        }

        if(name == null) {
            throw new IllegalArgumentException("citizenshipName is null");
        }

        TypedQuery<Country> query = em.createNamedQuery("findCountryByCodeAndName", Country.class);

        query.setParameter("code", code);
        query.setParameter("name", name);

        List<Country> countries = query.getResultList();

        if(countries.isEmpty()){
            throw new CountryNotFoundException(code, name);
        }

        return countries.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country findByCode(Integer code) {
        if(code == null) {
            throw new IllegalArgumentException("countryCode is null");
        }

        TypedQuery<Country> query = em.createNamedQuery("findCountryByCode", Country.class);
        query.setParameter("code", code);

        List<Country> countries = query.getResultList();

        if(countries.isEmpty()){
            throw new CountryNotFoundException(code);
        }

        return countries.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCountries(List<Country> countries) {
        TypedQuery<Country> query = em.createNamedQuery("findCountryByCode", Country.class);

        for(Country country : countries) {
            Integer code = country.getCode();
            String name = country.getName();

            query.setParameter("code", code);

            List<Country> result = query.getResultList();

            if(!result.isEmpty()){
                Country countryToUpdate = result.get(0);
                countryToUpdate.setName(name);
            } else {
                em.persist(country);
            }
        }
    }
}
