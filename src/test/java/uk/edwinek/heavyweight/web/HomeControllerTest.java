package uk.edwinek.heavyweight.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.edwinek.heavyweight.TestBase;
import uk.edwinek.heavyweight.config.HomeControllerTestConfig;
import uk.edwinek.heavyweight.service.Service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HomeControllerTestConfig.class})
class HomeControllerTest extends TestBase {

    @Autowired
    private HomeController homeController;

    @Autowired
    private Service service;

    private MockMvc mockController;

    @BeforeEach
    void setup() {
        mockController = standaloneSetup(homeController).build();
    }

    @Test
    void should_return_expected_reigns() throws Exception {

        String queryString = "2000-1-1";

        Mockito.when(service.getByDate(queryString)).thenReturn(buildValidHeavyweightResponse());
        MvcResult resultActions = mockController.perform(get("/query?date=" + queryString)).andReturn();

        assertThat("Expected header is returned.", resultActions.getResponse().getStatus(),
                equalTo(HttpStatus.OK.value()));
        assertThat("Expected body is returned.", resultActions.getResponse().getContentAsString(),
                equalTo(buildValidHeavyweightResponseString()));

    }

    @AfterEach
    void tearDown() {
        reset(service);
    }

}
