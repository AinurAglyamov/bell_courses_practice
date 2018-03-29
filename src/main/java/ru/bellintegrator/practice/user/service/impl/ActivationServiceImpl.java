package ru.bellintegrator.practice.user.service.impl;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.ActivationService;
import ru.bellintegrator.practice.user.service.EncodingService;

@Service
public class ActivationServiceImpl implements ActivationService {

    private EncodingService encodingService;
    private UserDao userDao;

    public ActivationServiceImpl() {
    }

    @Autowired
    public ActivationServiceImpl(EncodingService encodingService, UserDao userDao) {
        this.encodingService = encodingService;
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void activate(String activationCode) {
        if(Strings.isNullOrEmpty(activationCode)){
            throw new IllegalArgumentException("activationCode is wrong");
        }

        String code = encodingService.encode(activationCode);
        User user = userDao.findByCode(code);
        user.setActive(true);

    }
}
