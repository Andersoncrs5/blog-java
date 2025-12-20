package com.blog.writeapi.integration;

import com.blog.writeapi.HelperTest;
import com.blog.writeapi.dtos.tag.CreateTagDTO;
import com.blog.writeapi.repositories.TagRepository;
import com.blog.writeapi.utils.res.ResponseUserTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {
    private final String URL = "/v1/tag";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TagRepository repository;

    @Autowired
    private HelperTest helper;

    @BeforeEach
    void setup() {
        this.repository.deleteAll();
    }

    @Test
    void shouldCreateTag() throws Exception {
        ResponseUserTest userData = helper.loginSuperAdm();

        CreateTagDTO dto = this.getCreateTagDTO();

        mockMvc.perform(post(this.URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer " + userData.tokens().token()
                        ))
                .andExpect(status().isCreated())
                .andReturn();

    }

    public CreateTagDTO getCreateTagDTO() {
        Random random = new Random();

        char l = (char) ('a' + random.nextInt(26));
        char l2 = (char) ('a' + random.nextInt(26));
        char l3 = (char) ('a' + random.nextInt(26));
        char l4 = (char) ('a' + random.nextInt(26));
        char l5 = (char) ('a' + random.nextInt(26));
        char l6 = (char) ('a' + random.nextInt(26));
        char l7 = (char) ('a' + random.nextInt(26));
        char l8 = (char) ('a' + random.nextInt(26));
        char l9 = (char) ('a' + random.nextInt(26));
        char l10 = (char) ('a' + random.nextInt(26));

        return new CreateTagDTO(
                "software engineer " + l + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 + l10,
                "software-engineer-" + l + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 + l10,
                "",
                true,
                true,
                true
        );
    }

}
