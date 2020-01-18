package com.task.telna.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.telna.entity.Usage;
import com.task.telna.entity.User;
import com.task.telna.enums.UsageType;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UsageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UsageService usageService;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testSaveUsage() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");

        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        when(userService.findByUserId(1L)).thenReturn(user);
        when(usageService.saveUsage(any(Usage.class))).thenReturn(usage);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/usage").content(mapper.writeValueAsString(usage))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals(usage.getUsageType(), mapper.readValue(result.getResponse().getContentAsString(), Usage.class).getUsageType());

    }

    @Test
    public void testGetAllUserUsageHistory() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhoneNumber("981-124-2222");

        Usage request = new Usage(UsageType.DATA, new Date(), user);

        Usage usage = new Usage(UsageType.DATA, new Date(), user);
        List<Usage> usages = new ArrayList<>();
        usages.add(usage);
        when(userService.findByUserId(1L)).thenReturn(user);
        when(usageService.getAllUsageForAUser(any(Usage.class))).thenReturn(usages);
        mockMvc.perform(MockMvcRequestBuilders.post("/usage/history").content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1))).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usageType", is(UsageType.DATA.toString())));

    }
}
