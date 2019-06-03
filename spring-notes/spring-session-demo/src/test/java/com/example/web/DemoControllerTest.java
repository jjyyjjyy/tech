package com.example.web;

import com.example.SessionDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author jy
 */
@SpringBootTest(classes = SessionDemoApplication.class)
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${spring.application.name}")
    private String applicationName;

    @Test
    void testSessionHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andDo(MockMvcResultHandlers.log())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(applicationName))
            .andExpect(MockMvcResultMatchers.header().exists("X-Auth-Token"))
            .andReturn();
    }

}
