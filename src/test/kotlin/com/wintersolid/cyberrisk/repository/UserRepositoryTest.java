package com.wintersolid.cyberrisk.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.wintersolid.cyberrisk.model.User;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testGetUser() {
        User user = userRepository.getUser("testUser");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void testGetNonExistentUser() {
        User user = userRepository.getUser("nonExistentUser");
        assertNull(user);
    }
}
