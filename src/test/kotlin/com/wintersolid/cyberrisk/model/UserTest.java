package com.wintersolid.cyberrisk.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("testUser", "testPassword");

        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void testUserModification() {
        User user = new User("testUser", "testPassword");
        user.setUsername("newUser");
        user.setPassword("newPassword");

        assertEquals("newUser", user.getUsername());
        assertEquals("newPassword", user.getPassword());
    }
}
