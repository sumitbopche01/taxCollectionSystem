package com.finalEAMProject.dao;

import java.util.List;

import com.finalEAMProject.model.User;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void deleteUserById(Long id);

    void close();
}
