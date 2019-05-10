package com.aws.controller;

import com.aws.assignment.AssignmentApplication;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShortUrlControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    private static final String KEY="Adcses";

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void generateShortUrl() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/url/shorts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("url", "http://www.sina.com").param("comments", "sina")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().toString();
    }

    @Test
    public void redirect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/url/3UBR73")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON));
    }
}