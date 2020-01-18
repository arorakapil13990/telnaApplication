package com.task.telna.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.telna.entity.User;
import com.task.telna.service.UsageService;
import com.task.telna.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    User user = null;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");

        when(userService.saveUser(any(User.class))).thenReturn(1L);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/user").content(mapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals("1", result.getResponse().getContentAsString());
    }

}
