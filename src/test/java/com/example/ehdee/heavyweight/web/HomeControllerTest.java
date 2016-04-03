package com.example.ehdee.heavyweight.web;

import com.example.ehdee.heavyweight.TestBase;
import com.example.ehdee.heavyweight.config.HomeControllerTestConfig;
import com.example.ehdee.heavyweight.service.Service;
import com.example.ehdee.heavyweight.web.HomeController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HomeControllerTestConfig.class})
public class HomeControllerTest extends TestBase {

    @Autowired
    private HomeController homeController;

    @Autowired
    private Service service;

    private MockMvc mockController;

    @Before
    public void setup() {
        mockController = standaloneSetup(homeController).build();
    }

    @Test
    public void should_return_expected_reigns() throws Exception {

        String queryString = "2000-1-1";

        Mockito.when(service.getByDate(queryString)).thenReturn(buildValidReigns());
        MvcResult resultActions = mockController.perform(get("/query?date=" + queryString)).andReturn();

        Assert.assertThat("Expected header is returned.", resultActions.getResponse().getStatus(), equalTo(HttpStatus.OK.value()));
        Assert.assertThat("Expected body is returned.", resultActions.getResponse().getContentAsString(), equalTo(buildValidReignsString()));

    }

    @After
    public void tearDown() {
        reset(service);
    }

}
