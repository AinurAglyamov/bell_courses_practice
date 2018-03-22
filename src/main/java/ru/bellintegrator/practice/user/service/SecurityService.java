package ru.bellintegrator.practice.user.service;

public interface SecurityService {

    /**
     *
     * encode a value
     *
     * @param value
     * @return encoded value
     */
    String encode(String value);
}
