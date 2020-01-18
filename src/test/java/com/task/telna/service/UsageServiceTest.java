package com.task.telna.service;

import com.task.telna.constants.Constants;
import com.task.telna.entity.Usage;
import com.task.telna.entity.User;
import com.task.telna.enums.UsageType;
import com.task.telna.exception.UserNotFoundException;
import com.task.telna.repository.UsageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsageServiceTest {

    @InjectMocks
    private UsageService usageService;
    @Mock
    private UserService userService;
    @Mock
    private UsageRepository repository;


    @Test
    public void testGetAllUserUsage() throws UserNotFoundException, ParseException {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");
        List<Usage> usages = new ArrayList<>();
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        usages.add(usage);
        when(repository.findByUsageTypeAndUserAndStartDateBetween(any(UsageType.class), any(User.class),
                any(Date.class), any(Date.class))).thenReturn(usages);
        when(userService.findByUserId(1L)).thenReturn(user);
        usages = usageService.getAllUsageForAUser(usage);

        assertEquals(1, usages.size());
        assertEquals(UsageType.DATA, usages.get(0).getUsageType());

    }

    @Test
    public void testSaveUserUsage() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        when(repository.save(usage)).thenReturn(usage);
        when(userService.findByUserId(1L)).thenReturn(user);

        String successMessage = usageService.saveUsageInformation(usage);

        assertEquals(Constants.USAGE_SAVED_SUCCESSFULLY, successMessage);

    }

    @Test
    public void testUserNotFoundExceptionWhileSaving() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        when(userService.findByUserId(1L)).thenThrow(new UserNotFoundException(1L));
        assertThrows(UserNotFoundException.class, () -> usageService.saveUsageInformation(usage));

    }

    @Test
    public void testUserNotFoundExceptionWhileFetching() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        when(userService.findByUserId(1L)).thenThrow(new UserNotFoundException(1L));
        assertThrows(UserNotFoundException.class, () -> usageService.getAllUsageForAUser(usage));

    }
}
