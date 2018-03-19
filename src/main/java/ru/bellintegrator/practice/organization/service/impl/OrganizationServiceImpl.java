package ru.bellintegrator.practice.organization.service.impl;

import com.google.common.base.Strings;
import com.google.common.primitives.Longs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationFilter;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
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
        log.info("organization id = " + id);

        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }

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

        log.info(view.toString());

        return view;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationToSave view) {
        log.info(view.toString());

        Organization organization = new Organization();

        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        checkOrganization(organization);

        dao.save(organization);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView view) {
        log.info(view.toString());

        if(view.id == null) {
            throw new IllegalArgumentException("id is null");
        }/**/

        Organization organization = new Organization();

        organization.setId(view.id);
        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        checkOrganization(organization);

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
    public List<OrganizationView> list(OrganizationFilter view) {
        log.info(view.toString());

        if(view.name == null) {
            throw new IllegalArgumentException("orgName is null");
        }

        Organization organization = new Organization();

        organization.setName(view.name);
        organization.setInn(view.inn);
        organization.setActive(view.isActive);

        List<Organization> organizations = dao.list(organization);

        Function<Organization, OrganizationView> mapOrganization = o -> {
            OrganizationView organizationView = new OrganizationView();
            organizationView.id = o.getId();
            organizationView.name = o.getName();
            organizationView.isActive = o.isActive();

            log.info(organizationView.toString());

            return organizationView;
        };

        return organizations.stream().map(mapOrganization).collect(Collectors.toList());
    }

    private void checkOrganization(Organization organization){
        if(Strings.isNullOrEmpty(organization.getName())) {
            throw new IllegalArgumentException("orgName is wrong");
        }

        if(Strings.isNullOrEmpty(organization.getFullName())) {
            throw new IllegalArgumentException("orgFullName is wrong");
        }

        if(!checkInn(organization.getInn())) {
            throw new IllegalArgumentException("orgInn is wrong");
        }

        if(!checkKpp(organization.getKpp())) {
            throw new IllegalArgumentException("orgKpp is wrong");
        }

        if(!checkPhone(organization.getPhone())) {
            throw new IllegalArgumentException("orgPhone is wrong");
        }

        if(organization.isActive() == null) {
            throw new IllegalArgumentException("orgIsActive is wrong");
        }
    }

    private boolean checkInn(String inn) {
        if(inn == null) {
            return false;
        }
        return inn.matches("\\d{10}");
    }

    private boolean checkKpp(String kpp) {
        if(kpp == null) {
            return false;
        }
        return kpp.matches("\\d{9}");
    }

    private boolean checkPhone(String phone) {
        if(phone == null) {
            return false;
        }
        return phone.matches("^\\d[\\d\\(\\)\\ -]{8,20}\\d$");
    }


}
