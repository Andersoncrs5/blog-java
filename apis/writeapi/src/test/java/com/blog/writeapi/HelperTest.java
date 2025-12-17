package com.blog.writeapi;

import cn.hutool.core.lang.UUID;
import com.blog.writeapi.dtos.category.CategoryDTO;
import com.blog.writeapi.dtos.category.CreateCategoryDTO;
import com.blog.writeapi.dtos.user.CreateUserDTO;
import com.blog.writeapi.dtos.user.LoginUserDTO;
import com.blog.writeapi.dtos.user.UserDTO;
import com.blog.writeapi.utils.res.ResponseHttp;
import com.blog.writeapi.utils.res.ResponseTokens;
import com.blog.writeapi.utils.res.ResponseUserTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Service
@RequiredArgsConstructor
public class HelperTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public CategoryDTO createCategory(ResponseUserTest userData, Long parentId) throws Exception {
        String URL = "/v1/category";

        Random random = new Random();

        char letter = (char) ('a' + random.nextInt(26));
        char letter2 = (char) ('a' + random.nextInt(26));
        char letter3 = (char) ('a' + random.nextInt(26));
        char letter4 = (char) ('a' + random.nextInt(26));
        char letter5 = (char) ('a' + random.nextInt(26));
        char letter6 = (char) ('a' + random.nextInt(26));

        CreateCategoryDTO dto = new CreateCategoryDTO(
                "software engineer " + letter + letter2 + letter3 + letter4 + letter5 + letter6,
                "",
                "software-engineer-" + letter + letter2 + letter3 + letter4 + letter5 + letter6,
                true,
                true,
                0,
                parentId
        );

        MvcResult result = this.mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer " + userData.tokens().token()
                        ))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        TypeReference<ResponseHttp<CategoryDTO>> typeRef = new TypeReference<>() {};

        ResponseHttp<CategoryDTO> response =
                objectMapper.readValue(json, typeRef);

        assertThat(response.message()).isNotBlank();
        assertThat(response.status()).isEqualTo(true);
        assertThat(response.data().name()).isEqualTo(dto.name());
        assertThat(response.data().description()).isEqualTo(dto.description());
        assertThat(response.data().slug()).isEqualTo(dto.slug());
        assertThat(response.data().isActive()).isEqualTo(dto.isActive());
        assertThat(response.data().visible()).isEqualTo(dto.visible());
        assertThat(response.data().displayOrder()).isEqualTo(dto.displayOrder());

        return response.data();
    }

    public ResponseUserTest loginSuperAdm() throws Exception {
        String URL = "/v1/auth/";

        LoginUserDTO dto = new LoginUserDTO(
                "system.domain@gmail.com",
                "0123456789"
        );

        MvcResult result = mockMvc.perform(post(URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        String registerJson = result.getResponse().getContentAsString();
        TypeReference<ResponseHttp<ResponseTokens>> typeRef =
                new TypeReference<>() {};

        ResponseHttp<ResponseTokens> response =
                objectMapper.readValue(registerJson, typeRef);

        assertThat(response.status()).isEqualTo(true);
        assertThat(response.message()).isNotBlank();
        assertThat(response.data().token()).isNotBlank();
        assertThat(response.data().refreshToken()).isNotBlank();


        MvcResult resultGet = mockMvc.perform(get("/v1/user/me")
                        .header("Authorization", "Bearer " + response.data().token()))
                .andExpect(status().isOk()).andReturn();

        String json = resultGet.getResponse().getContentAsString();
        TypeReference<ResponseHttp<UserDTO>> typeRefGet = new TypeReference<>() {};

        ResponseHttp<UserDTO> responseGet =
                objectMapper.readValue(json, typeRefGet);

        return new ResponseUserTest(
                response.data(),
                null,
                responseGet.data()
        );

    }

    public ResponseUserTest createUser() {
        try {
            String key = UUID.fastUUID().toString();

            CreateUserDTO dto = new CreateUserDTO(
                    "name" + key,
                    "username" + key,
                    "user" + key + "@gmail.com",
                    "12345678"
            );

            MvcResult result = mockMvc.perform(post("/v1/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated())
                    .andReturn();

            String registerJson = result.getResponse().getContentAsString();
            TypeReference<ResponseHttp<ResponseTokens>> typeRef =
                    new TypeReference<>() {
                    };

            ResponseHttp<ResponseTokens> response =
                    objectMapper.readValue(registerJson, typeRef);

            assertThat(response.status()).isEqualTo(true);
            assertThat(response.message()).isNotBlank();
            assertThat(response.data().token()).isNotBlank();
            assertThat(response.data().refreshToken()).isNotBlank();

            MvcResult resultGet = mockMvc.perform(get("/v1/user/me")
                            .header("Authorization", "Bearer " + response.data().token()))
                    .andExpect(status().isOk()).andReturn();

            String json = resultGet.getResponse().getContentAsString();
            TypeReference<ResponseHttp<UserDTO>> typeRefGet = new TypeReference<>() {};

            ResponseHttp<UserDTO> responseGet =
                    objectMapper.readValue(json, typeRefGet);

            return new ResponseUserTest(
                    response.data(),
                    dto,
                    responseGet.data()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
