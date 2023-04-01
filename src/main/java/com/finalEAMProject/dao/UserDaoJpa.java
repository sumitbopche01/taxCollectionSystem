package com.finalEAMProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.finalEAMProject.model.User;

public class UserDaoJpa implements UserDao {

    private EntityManager entityManager;

    public UserDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUserById(Long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        entityManager.close();
    }
}
