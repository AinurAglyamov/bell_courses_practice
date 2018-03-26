package ru.bellintegrator.practice.user.service;

public interface ActivationService {

    /**
     *
     * activate the User
     *
     * @param activationCode
     */
    public void activate(String activationCode);

}
