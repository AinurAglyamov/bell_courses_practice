package ru.bellintegrator.practice.organization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
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


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationView view) {
        Organization organization = new Organization();

        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        dao.save(organization);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView view) {
        log.info(view.toString());

        Organization organization = new Organization();

        organization.setId(view.id);
        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        dao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OrganizationView> list(OrganizationView view) {
        Organization organization = new Organization();

        organization.setName(view.name);
        organization.setInn(view.inn);
        organization.setActive(view.isActive);

        List<Organization> organizations = dao.list(organization);

        Function<Organization, OrganizationView> mapOrganization = o -> {
            OrganizationView organizationView = new OrganizationView();
            organizationView.id = o.getId();
            organizationView.name = o.getName();
            organizationView.fullName = o.getFullName();
            organizationView.inn = o.getInn();
            organizationView.kpp = o.getKpp();
            organizationView.address = o.getAddress();
            organizationView.phone = o.getPhone();
            organizationView.isActive = o.isActive();

            return organizationView;
        };

        return organizations.stream().map(mapOrganization).collect(Collectors.toList());
    }
}
