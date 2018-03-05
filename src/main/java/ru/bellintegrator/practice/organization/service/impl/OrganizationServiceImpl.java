package ru.bellintegrator.practice.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView loadById(Long id) {
        Organization organization = dao.loadById(id);


        OrganizationView view = new OrganizationView();

        view.id = organization.getId();
        view.name = organization.getName();
        view.fullName = organization.getFullName();
        view.inn = organization.getInn();
        view.kpp = organization.getKpp();
        view.address = organization.getAddress();
        view.phone = organization.getPhone();
        view.isActive = organization.isActive();

        return view;
    }
}
