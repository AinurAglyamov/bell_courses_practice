package ru.bellintegrator.practice.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.user.service.EncodingService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncodingServiceImpl implements EncodingService {

    private final Logger log = LoggerFactory.getLogger(EncodingServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(String value) {

        byte[] digest = null;

        try {
            MessageDigest encoder = MessageDigest.getInstance("SHA-256");
            digest = encoder.digest(value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            log.error(null, e);
        }

        return new String(Base64.getEncoder().encode(digest));
    }
}
