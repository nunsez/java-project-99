package hexlet.code.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.util.ModelGenerator;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelGenerator modelGenerator;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private User testUser;

    @BeforeEach
    public void beforeEach() {
        testUser = Instancio.create(modelGenerator.userModel());
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        var request = post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testUser));
        var response = mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andReturn();

        var body = response.getResponse().getContentAsString();
        assertThatJson(body).and(
            v -> v.node("firstName").isEqualTo(testUser.getFirstName()),
            v -> v.node("lastName").isEqualTo(testUser.getLastName()),
            v -> v.node("email").isEqualTo(testUser.getEmail()),
            v -> v.node("password").isNotEqualTo(testUser.getPassword()),
            v -> v.node("createdAt").isNotNull()
        );

        var user = userRepository.findByEmail(testUser.getEmail()).get();
        assertThat(user.getFirstName()).isEqualTo(testUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(testUser.getLastName());
        assertThat(user.getUpdatedAt()).isEqualTo(user.getCreatedAt());
    }

    @Test
    public void testPartialUpdate() throws Exception {
        userRepository.save(testUser);
        var data = Map.of("firstName", "Alex");
        var request = put("/api/users/" + testUser.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(data));

        var response = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        var body = response.getResponse().getContentAsString();
        assertThatJson(body).and(
            v -> v.node("firstName").isEqualTo("Alex"),
            v -> v.node("lastName").isEqualTo(testUser.getLastName())
        );

        var user = userRepository.findById(testUser.getId()).get();
        assertThat(user.getFirstName()).isEqualTo("Alex");
        assertThat(user.getLastName()).isEqualTo(testUser.getLastName());
        assertThat(user.getUpdatedAt()).isAfter(user.getCreatedAt());
    }
}
