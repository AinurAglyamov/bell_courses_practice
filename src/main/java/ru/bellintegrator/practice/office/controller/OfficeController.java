package ru.bellintegrator.practice.office.controller;


import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.office.view.OfficeFilter;
import ru.bellintegrator.practice.office.view.OfficeToSave;
import ru.bellintegrator.practice.office.view.OfficeView;

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
     * create Office
     *
     * @param office
     */
    OfficeView createOffice(@RequestBody OfficeToSave office);

    /**
     * update Office
     *
     * @param office
     */
    void updateOffice(@RequestBody OfficeView office);

    /**
     *
     * delete Office
     *
     * @param id
     */
    void deleteOffice(Long id);

    /**
     *
     * get Office list
     *
     * @param office
     * @return JSON offices value
     */
    List<OfficeView> list(OfficeFilter office);




}
