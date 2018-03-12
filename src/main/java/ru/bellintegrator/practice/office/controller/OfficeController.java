package ru.bellintegrator.practice.office.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;

import java.util.List;

public interface OfficeController {

    /**
     *
     * get Office by id
     *
     * @param id
     * @return JSON office value
     */
    OfficeView officeById(Long id);


    /**
     * save Office
     *
     * @param office
     */
    void saveOffice(@RequestBody OfficeToSave office);

    /**
     * update Office
     *
     * @param office
     */
    void updateOffice(@RequestBody OfficeView office);

    /**
     * delete Office
     *
     * @param id
     */
    void deleteOffice(@RequestBody Long id);

    /**
     *
     * get Office list
     *
     * @param office
     * @return JSON offices value
     */
    List<OfficeView> list(OfficeFilter office);




}
