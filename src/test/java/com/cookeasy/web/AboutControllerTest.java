package com.cookeasy.web;

import com.cookeasy.constant.GlobalTestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AboutControllerTest {
    private final MockMvc mockMvc;

    @Autowired
    public AboutControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testRetrieveAboutPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/about")).
                andExpect(status().isOk()).
                andExpect(view().name(GlobalTestConstants.ABOUT_PAGE_VIEW_NAME));
    }
}