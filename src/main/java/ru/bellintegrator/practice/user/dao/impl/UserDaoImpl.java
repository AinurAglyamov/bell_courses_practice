package ru.bellintegrator.practice.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.error.UserAlreadyExistsException;
import ru.bellintegrator.practice.user.error.UserNotFoundException;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        log.info(user.toString());

        if(!findByLogin(user.getLogin()).isEmpty()){
            throw new UserAlreadyExistsException(user.getLogin());
        }

        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findByLogin(String login) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        return users;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByCode(String code) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.code = :code", User.class);
        query.setParameter("code", code);

        List<User> users = query.getResultList();

        if(users.isEmpty()){
            throw new UserNotFoundException("Пользователь с заданным кодом не существует");
        }

        return users.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByLoginAndPassword(String login, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);

        List<User> users = query.getResultList();

        if(users.isEmpty()){
            throw new UserNotFoundException("Пользователь с заданными логином и паролем не существует");
        }

        return users.get(0);
    }

    /*public List<User> list(){
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        return users;
    }*/
}
