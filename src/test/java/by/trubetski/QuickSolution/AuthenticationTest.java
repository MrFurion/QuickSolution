package by.trubetski.QuickSolution;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("auth/login"));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/process_login")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("username", "Jerry")
                        .param("password", "4444"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/hello")); // Поменяйте на вашу целевую страницу
    }

    @Test
    public void testLoginFailure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/process_login")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("username", "Jerry")
                        .param("password", "123123"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/auth/login?error=true"));
    }
}
