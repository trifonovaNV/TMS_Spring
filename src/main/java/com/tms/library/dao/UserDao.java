package com.tms.library.dao;


import com.tms.library.model.User;

public interface UserDao {
    User findByUsername(String username);
    User addUser(User user);
}
