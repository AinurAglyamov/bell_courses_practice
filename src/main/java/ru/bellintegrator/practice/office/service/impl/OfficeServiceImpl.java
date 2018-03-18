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
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private OfficeDao dao;
    private OrganizationDao orgDao;

    @Autowired
    public void setDao(OfficeDao dao) {
        this.dao = dao;
    }

    @Autowired
    public void setOrgDao(OrganizationDao orgDao) {
        this.orgDao = orgDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView loadById(Long id) {
        log.info("id = " + id);

        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Office office = dao.loadById(id);


        OfficeView view = new OfficeView();

        view.id = office.getId();
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
    public void save(OfficeToSave view) {
        log.info("Office to save:" + view.toString());

        if(view.orgId == null) {
            throw new IllegalArgumentException("orgId is null");
        }/**/

        Office office = new Office();

        office.setName(view.name);
        office.setAddress(view.address);
        office.setPhone(view.phone);
        office.setActive(view.isActive);

        orgDao.loadById(view.orgId).addOffice(office);

        checkOffice(office);

        dao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView view) {
        log.info("updated Office" + view.toString());

        if(view.id == null) {
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
        if(id == null) {
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

        if(view.orgId == null) {
            throw new IllegalArgumentException("orgId is null");
        }

        orgDao.loadById(view.orgId);

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

            log.info(officeView.toString());

            return officeView;
        };

        return offices.stream().map(mapOffice).collect(Collectors.toList());
    }

    private void checkOffice(Office office){
        if(Strings.isNullOrEmpty(office.getName())) {
            throw new IllegalArgumentException("officeName is null or empty");
        }

        if(Strings.isNullOrEmpty(office.getPhone())) {
            throw new IllegalArgumentException("officePhone is null or empty");
        }

        if(office.isActive() == null) {
            throw new IllegalArgumentException("officeIsActive is null");
        }

    }
}
