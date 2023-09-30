package event.ggp.local.event.controller.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        // @formatter:off
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                // Spring Securityの設定をテストに反映させるために必要
                .apply(springSecurity()).build();
    }
    // @formatter:on
     
    void testExecute_Management_Register() {

    }

    @Test
    void testExecute_Management_UserDelete() {

    }

    @Test
    @DisplayName("ユーザ編集画面にユーザ情報なしで遷移する")
    @WithMockUser(username = "user", roles = { "ADMIN" })
    void testExecute_Management_UserModify() throws Exception {
        this.mockMvc.perform(get("/management/usermodify")) 
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .andExpect(view().name("admin"))
            .andExpect(status().isOk());
        // @formatter:on

    }

    @Test
    void testShow_Management_Register() {

    }

    @Test
    void testShow_Management_UserDelete() {

    }

    @Test
    void testShow_Management_UserList() {

    }

    @Test
    void testShow_Management_UserModify() {

    }

    @Test
    void testShow_self_Management_UserModify() {

    }
}
