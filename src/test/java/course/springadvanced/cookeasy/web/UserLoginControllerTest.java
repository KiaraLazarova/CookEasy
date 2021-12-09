package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.constant.GlobalTestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginControllerTest {
    private final MockMvc mockMvc;

    @Autowired
    public UserLoginControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testRetrieveLoginPageShouldReturnCorrectView() throws Exception {
        this.mockMvc.
                perform(get("/users/login")).
                andExpect(status().isOk()).
                andExpect(view().name(GlobalTestConstants.LOGIN_PAGE_VIEW_NAME));
    }
}