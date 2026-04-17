package com.elson.validador_senha.Infrastructure;

import com.elson.validador_senha.application.dto.ValidationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PasswordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnValidForCorrectPassword() throws Exception {
        ValidationRequest request = new ValidationRequest("AbTp9!fok");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true))
                .andExpect(jsonPath("$.message").value("sua senha é valida"));
    }

    @Test
    void shouldReturnInvalidForPasswordWithRepeatedChar() throws Exception {
        ValidationRequest request = new ValidationRequest("AbTp9!foA");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha não pode conter caracteres repetidos"));
    }

    @Test
    void shouldReturnInvalidForPasswordWithSpace() throws Exception {
        ValidationRequest request = new ValidationRequest("AbTp9 fok");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha não pode conter espaços em branco"));
    }

    @Test
    void shouldReturnInvalidForShortPassword() throws Exception {
        ValidationRequest request = new ValidationRequest("Ab1!");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha deve ter pelo menos 9 caracteres"));
    }

    @Test
    void shouldReturnInvalidForPasswordWithoutDigit() throws Exception {
        ValidationRequest request = new ValidationRequest("AbTp!fok");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha deve conter pelo menos um dígito (0-9)"));
    }

    @Test
    void shouldReturnInvalidForPasswordWithoutUppercase() throws Exception {
        ValidationRequest request = new ValidationRequest("abtp9!fok");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha deve conter pelo menos uma letra maiúscula (A-Z)"));
    }

    @Test
    void shouldReturnInvalidForPasswordWithoutLowercase() throws Exception {
        ValidationRequest request = new ValidationRequest("ABTP9!FOK");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha deve conter pelo menos uma letra minúscula (a-z)"));
    }

    @Test
    void shouldReturnInvalidForPasswordWithoutSpecialChar() throws Exception {
        ValidationRequest request = new ValidationRequest("AbTp9fokX");

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false))
                .andExpect(jsonPath("$.message").value("A senha deve conter pelo menos um caractere especial: !@#$%^&*()-+"));
    }

    @Test
    void shouldReturnInvalidForNullPassword() throws Exception {
        ValidationRequest request = new ValidationRequest(null);

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenPasswordFieldIsMissing() throws Exception {
        String jsonWithoutPassword = "{}";

        mockMvc.perform(post("/api/v1/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonWithoutPassword))
                .andExpect(status().isBadRequest());
    }
}