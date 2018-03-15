package ru.bellintegrator.practice.reference.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.reference.dao.CountryDao;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.service.CountryService;
import ru.bellintegrator.practice.reference.view.CountryView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao dao;

    @Autowired
    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<CountryView> all() {
        List<Country> countries = dao.all();

        Function<Country, CountryView> mapCountry = c -> {
          return new CountryView(c.getName(), c.getCode());
        };

        return countries.stream().map(mapCountry).collect(Collectors.toList());
    }
}
