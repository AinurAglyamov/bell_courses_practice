package ru.bellintegrator.practice.reference.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountryNotFoundException extends ReferenceException{

    Logger log = LoggerFactory.getLogger(CountryNotFoundException.class);

    public CountryNotFoundException(Integer code, String name) {
        super("Country with code = " + code + " and with name = " + name + " not found");
    }
}
