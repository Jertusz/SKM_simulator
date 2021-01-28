package pl.edu.pjwstk.simulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pjwstk.simulator.security.RegisterController;
import pl.edu.pjwstk.simulator.security.User;
import pl.edu.pjwstk.simulator.security.UserController;
import pl.edu.pjwstk.simulator.security.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RegisterController registerController;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationManager authenticationManager;


    @Test
    public void addUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"admin\", \"password\":\"pass\"}")).andExpect(status().isOk());
        Mockito.verify(userService).createOrUpdate(Mockito.any(User.class));
    }

    @Test
    public void addUserWithExistingUsername() throws Exception {
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User());
        mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"admin\", \"password\":\"pass\"}")).andExpect(status().isConflict());
    }

}
