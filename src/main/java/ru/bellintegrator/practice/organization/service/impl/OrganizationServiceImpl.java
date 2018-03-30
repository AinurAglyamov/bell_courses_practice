package ru.bellintegrator.practice.organization.service.impl;

import com.google.common.base.Strings;
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
import ru.bellintegrator.practice.reference.dao.CountryDao;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private OrganizationDao organizationDao;
    private CountryDao countryDao;

    public OrganizationServiceImpl() {
    }

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, CountryDao countryDao) {
        this.organizationDao = organizationDao;
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView loadById(Long id) {
        log.info("organization id = " + id);

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Organization organization = organizationDao.loadById(id);

        OrganizationView view = new OrganizationView();

        view.id = organization.getId();
        view.name = organization.getName();
        view.fullName = organization.getFullName();
        view.inn = organization.getInn();
        view.kpp = organization.getKpp();
        view.countryCode = organization.getCountry().getCode();
        view.countryName = organization.getCountry().getName();
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
    public OrganizationView save(OrganizationToSave view) {
        log.info(view.toString());

        Organization organization = new Organization();

        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setCountry(countryDao.findByCode(view.countryCode));
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        checkOrganization(organization);

        organizationDao.save(organization);

        OrganizationView organizationView = new OrganizationView();
        organizationView.id = organization.getId();

        log.info("id of saved organization = " + organizationView.id);

        return organizationView;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView view) {
        log.info(view.toString());

        if (view.id == null) {
            throw new IllegalArgumentException("id is null");
        }/**/

        Organization organization = new Organization();

        organization.setId(view.id);
        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setCountry(countryDao.findByCode(view.countryCode));
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        checkOrganization(organization);

        organizationDao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("orgId is null");
        }
        organizationDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OrganizationView> list(OrganizationFilter view) {
        log.info(view.toString());

        checkFilterParams(view);

        Organization organization = new Organization();

        organization.setName(view.name);
        organization.setInn(view.inn);
        organization.setActive(view.isActive);

        List<Organization> organizations = organizationDao.list(organization);

        Function<Organization, OrganizationView> mapOrganization = o -> {
            OrganizationView organizationView = new OrganizationView();
            organizationView.id = o.getId();
            organizationView.name = o.getName();
            organizationView.isActive = o.isActive();
            organizationView.officesCount = o.getOffices().size();

            log.info(organizationView.toString());

            return organizationView;
        };

        return organizations.stream().map(mapOrganization).collect(Collectors.toList());
    }

    private void checkOrganization(Organization organization) {

        String fullName = organization.getFullName();
        String inn = organization.getInn();
        String kpp = organization.getKpp();
        String address = organization.getAddress();
        String phone = organization.getPhone();

        if (Strings.isNullOrEmpty(fullName)) {
            throw new IllegalArgumentException("orgFullName is wrong");
        }

        if ((inn == null) || (!checkInn(inn))) {
            throw new IllegalArgumentException("orgInn is wrong");
        }

        if ((kpp == null) || (!checkKpp(kpp))) {
            throw new IllegalArgumentException("orgKpp is wrong");
        }

        if (Strings.isNullOrEmpty(address)) {
            throw new IllegalArgumentException("address is wrong");
        }

        if ((phone != null) && (!checkPhone(phone))) {
            throw new IllegalArgumentException("orgPhone is wrong");
        }



    }

    private void checkFilterParams(OrganizationFilter filter) {
        String name = filter.name;
        String inn = filter.inn;
        Boolean isActive = filter.isActive;

        if((!Strings.isNullOrEmpty(name)) || (!Strings.isNullOrEmpty(inn)) || (isActive != null)){
            if(Strings.isNullOrEmpty(name)){
                throw new IllegalArgumentException("orgName is null");
            }
            if ((!Strings.isNullOrEmpty(inn)) && (!checkInn(inn))) {
                throw new IllegalArgumentException("orgInn is wrong");
            }
        }

    }

    private boolean checkInn(String inn) {
        return inn.matches("\\d{10}");
    }

    private boolean checkKpp(String kpp) {
        return kpp.matches("\\d{9}");
    }

    private boolean checkPhone(String phone) {
        return phone.matches("^\\d[\\d\\(\\)\\ -]{8,20}\\d$");
    }


}
