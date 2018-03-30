package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

public interface OfficeService {

    /**
     *
     * get Office by id
     *
     * @param id
     * @return JSON organization value
     */
    OfficeView loadById(Long id);

    /**
     *
     * save Office
     *
     * @param view
     * @return office view
     */
    OfficeView save(OfficeToSave view);

    /**
     *
     * update Office
     *
     * @param view
     *
     */
    void update(OfficeView view);

    /**
     *
     * delete Office
     *
     * @param id
     *
     */
    void delete(Long id);

    /**
     * get Offices
     *
     * @param view
     * @return JSON offices value
     */
    List<OfficeView> list(OfficeFilter view);


}
