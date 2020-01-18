package com.task.telna.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();


    @Test
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
    public void testSaveUsersUsage() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("newtest");
        user.setEmail("newtest@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/usage").content(mapper.writeValueAsString(usage))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals(usage.getUsageType(), mapper.readValue(result.getResponse().getContentAsString(), Usage.class).getUsageType());

    }

    @Test
    public void testGetAllUsageForAUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("newtest");
        user.setEmail("newtest@gmail.com");
        user.setPhoneNumber("981-124-2222");
        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/usage/history").content(mapper.writeValueAsString(usage))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(status().isOk()).andReturn();

        List<Usage> usages = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Usage>>() {});
        assertEquals(usage.getUsageType(), usages.get(0).getUsageType());
    }

}
