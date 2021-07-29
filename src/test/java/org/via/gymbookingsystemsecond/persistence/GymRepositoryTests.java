package org.via.gymbookingsystemsecond.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.via.gymbookingsystemsecond.util.RandomUtils.randAccount;
import static org.via.gymbookingsystemsecond.util.RandomUtils.randGym;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.via.gymbookingsystemsecond.domain.Gym;

@SpringBootTest
@AutoConfigureMockMvc
public class GymRepositoryTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GymRepository repo;

    @Test
    void testAddGym() throws Exception {
        Gym gym = objectMapper.readValue(
                mockMvc.perform(
                        post("/gyms/createGym")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(randGym()))
                ).andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                , Gym.class);

        System.out.println("-----------------------------------------------");
        System.out.println(gym);
        System.out.println("-----------------------------------------------");

        Gym resp = repo.findById(gym.getId()).orElseThrow();

        assertNotNull(resp);
        assertEquals(gym.getName(), resp.getName());
        assertEquals(gym.getHourlyLimit(), resp.getHourlyLimit());
    }
}
