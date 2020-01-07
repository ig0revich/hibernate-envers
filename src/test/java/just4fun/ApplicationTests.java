package just4fun;

import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.jdbc.core.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SneakyThrows
    @Test
    void postGoodEventTest() {
        mockMvc.perform(get("/api/goodEvents")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$._embedded.goodEvents", hasSize(0)));

        mockMvc.perform(post("/api/goodEvents")
            .content("{\"name\": \"Bought BVG Start Ticket\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        mockMvc.perform(get("/api/goodEvents")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$._embedded.goodEvents", hasSize(1)));

        assertTrue(jdbcTemplate.queryForList("select * from revinfo").size() == 1);
        assertEquals("Bought BVG Start Ticket",
            jdbcTemplate.queryForList("select * from good_events_aud").get(0).get("NAME"));
    }

}
