package com.wintersolid.cyberrisk.repository;

import com.wintersolid.cyberrisk.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> userDatabase = new HashMap<>();

    public UserRepository() {
        // Adding a default user for testing
        userDatabase.put("testUser", new User("testUser", "testPassword"));
    }

    public User getUser(String username) {
        return userDatabase.get(username);
    }
}