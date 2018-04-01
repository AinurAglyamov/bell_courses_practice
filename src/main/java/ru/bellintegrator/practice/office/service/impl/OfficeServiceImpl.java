package ru.bellintegrator.practice.office.service.impl;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private OfficeDao dao;
    private OrganizationDao orgDao;

    public OfficeServiceImpl() {
    }

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao orgDao) {
        this.dao = dao;
        this.orgDao = orgDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView loadById(Long id) {
        log.info("id = " + id);

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Office office = dao.loadById(id);

        OfficeView view = new OfficeView();

        view.id = office.getId();
        view.orgId = office.getOrganization().getId();
        view.name = office.getName();
        view.address = office.getAddress();
        view.phone = office.getPhone();
        view.isActive = office.isActive();

        log.info(view.toString());

        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView save(OfficeToSave view) {
        log.info("Office to save:" + view.toString());

        if(view.orgId == null) {
            throw new IllegalArgumentException("officeId is null");
        }

        Office office = new Office();

        office.setName(view.name);
        office.setAddress(view.address);
        office.setPhone(view.phone);
        office.setActive(view.isActive);

        orgDao.loadById(view.orgId).addOffice(office);

        checkOffice(office);

        dao.save(office);

        OfficeView officeView = new OfficeView();
        officeView.id = office.getId();

        return officeView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView view) {
        log.info("updated Office" + view.toString());

        if (view.id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Office office = new Office();

        office.setId(view.id);
        office.setName(view.name);
        office.setAddress(view.address);
        office.setPhone(view.phone);
        office.setActive(view.isActive);

        checkOffice(office);

        dao.update(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> list(OfficeFilter view) {
        log.info("Filter: " + view.toString());

        checkFilterParams(view);

        Office office = new Office();

        office.getOrganization().setId(view.orgId);
        office.setName(view.name);
        office.setPhone(view.phone);
        office.setActive(view.isActive);

        List<Office> offices = dao.list(office);

        Function<Office, OfficeView> mapOffice = o -> {
            OfficeView officeView = new OfficeView();
            officeView.id = o.getId();
            officeView.name = o.getName();
            officeView.isActive = o.isActive();
            officeView.organizationName = o.getOrganization().getName();

            log.info(officeView.toString());

            return officeView;
        };

        return offices.stream().map(mapOffice).collect(Collectors.toList());
    }

    private void checkOffice(Office office) {
        String name = office.getName();
        String address = office.getAddress();
        String phone = office.getPhone();

        StringBuilder errorMessage = new StringBuilder(200);

        if (Strings.isNullOrEmpty(name)) {
            errorMessage.append("officeName is wrong; ");
        }

        if (Strings.isNullOrEmpty(address)) {
            errorMessage.append("officeAddress is wrong; ");
        }

        if ((phone != null) && (!checkPhone(phone))) {
            errorMessage.append("officePhone is wrong; ");
        }

        if(errorMessage.length() != 0) {
            throw new IllegalArgumentException(errorMessage.toString());
        }

    }

    private void checkFilterParams(OfficeFilter filter) {
        Long orgId = filter.orgId;
        String name = filter.name;
        String phone = filter.phone;
        Boolean isActive = filter.isActive;

        if((orgId != null) || (!Strings.isNullOrEmpty(name)) || (!Strings.isNullOrEmpty(phone)) || (isActive != null)){

            orgDao.loadById(orgId);

            if((!Strings.isNullOrEmpty(phone)) && (!checkPhone(phone))){
                throw new IllegalArgumentException("officePhone is wrong");
            }
        }
    }

    private boolean checkPhone(String phone) {
        return phone.matches("^\\d[\\d\\(\\)\\ -]{8,20}\\d$");
    }
}
