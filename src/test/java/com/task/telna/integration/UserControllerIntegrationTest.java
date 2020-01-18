package com.task.telna.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.telna.constants.Constants;
import com.task.telna.entity.Usage;
import com.task.telna.entity.User;
import com.task.telna.enums.UsageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    @Order(1)
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setName("newtest");
        user.setEmail("newtest@gmail.com");
        user.setPhoneNumber("981-124-2222");
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post("/user").content(mapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals("1", result.getResponse().getContentAsString());
    }

    @Test
    @Order(2)
    public void testSaveUsersUsage() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("newtest");
        user.setEmail("newtest@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        MvcResult usageResult = mvc
                .perform(MockMvcRequestBuilders.post("/usage").content(mapper.writeValueAsString(usage))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals(Constants.USAGE_SAVED_SUCCESSFULLY, usageResult.getResponse().getContentAsString());

    }

    @Test
    @Order(3)
    public void testGetAllUsageForAUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("newtest");
        user.setEmail("newtest@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        mvc.perform(MockMvcRequestBuilders.post("/usage/history").content(mapper.writeValueAsString(usage))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usageType", is(UsageType.DATA.toString())));

    }

}
