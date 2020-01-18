package com.task.telna.service;

import com.task.telna.entity.User;
import com.task.telna.exception.EmailIdAlreadyExistException;
import com.task.telna.exception.UserNotFoundException;
import com.task.telna.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository repository;


    @Test
    public void testCreateUser() throws EmailIdAlreadyExistException {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUserId(1);
        when(repository.findByEmail(any(String.class))).thenReturn(null);
        when(repository.save(any(User.class))).thenReturn(user);
        assertEquals(1, userService.saveUser(user));
    }


    @Test
    public void testIfEmailAlreadyExist() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUserId(1);
        when(repository.findByEmail(any(String.class))).thenReturn(user);
        assertThrows(EmailIdAlreadyExistException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUserId(1);
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        assertNotNull(userService.findByUserId(1L));
    }

}
