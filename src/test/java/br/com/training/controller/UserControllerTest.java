package br.com.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.training.dto.request.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setUp() {
        LocalDate date = LocalDate.parse("1991-06-06");
        this.userRequestDTO = new UserRequestDTO("Gleydson", "email@email", "60922599580", date);
    }

    @Test
    @DisplayName("Sucesso se NÃO achar o usuário")
    public void getUserNotFound() throws Exception {
        mockMvc.perform(get("/users/{cpf}", "60922599580"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Sucesso se INSERIR o usuario")
    public void insertUser() throws Exception {
        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(userRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Sucesso se DELETAR o usuário")
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/{cpf}", "60922599580"))
                .andExpect(status().isNoContent());
    }
}
