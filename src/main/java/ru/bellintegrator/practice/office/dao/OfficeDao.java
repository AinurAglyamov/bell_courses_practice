package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

/**
 *
 * DAO для работы с Office
 *
 */
public interface OfficeDao {
    /**
     *
     * get Office by id
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     *
     * add Office
     *
     * @param office
     */
    void save(Office office);

    /**
     *
     * update Office
     *
     * @param office
     */
    void update(Office office);

    /**
     *
     * delete Office
     *
     * @param id
     */
    void delete(Long id);

    /**
     *
     * get Offices
     *
     * @param office
     * @return
     */
    List<Office> list(Office office);
}
