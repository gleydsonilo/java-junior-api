package br.com.training.controller;

import br.com.training.dto.response.UserResponseDTO;
import br.com.training.service.UserService;
import br.com.training.service.exception.InvalidArgumentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.training.dto.request.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponse;

    @BeforeEach
    void setUp() {
        LocalDate date = LocalDate.parse("1991-06-06");
        this.userResponse = new UserResponseDTO("Name", "email@email", "60922599580", date);
        this.userRequestDTO = new UserRequestDTO("Name", "email@email", "60922599580", date);
    }

    @Test
    @DisplayName("Sucesso se NÃO encontrar o usuário")
    public void getUserNotFound() throws Exception {
        Mockito.when(userService.findByCpf("123456789")).thenThrow(InvalidArgumentException.class);

        mockMvc.perform(get("/users/{cpf}", "123456789"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Sucesso se encontrar o usuário")
    public void getUser() throws Exception {
        Mockito.when(userService.findByCpf("60922599580")).thenReturn(userResponse);

        mockMvc.perform(get("/users/{cpf}", "60922599580"))
                .andExpect(status().isOk());
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

    @Test
    @DisplayName("Sucesso se ATUALIZAR o usuario")
    public void updateUser() throws Exception {
        mockMvc.perform(put("/users/{cpf}", "60922599580")
                .content(objectMapper.writeValueAsString(userRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Sucesso se NÃO achar o usuário para ATUALIZAR")
    public void updateUserNotFound() throws Exception {
        Mockito.when(userService.findByCpf("123456789")).thenThrow(InvalidArgumentException.class);
        mockMvc.perform(put("/users/{cpf}", "123456789")
                .content(objectMapper.writeValueAsString(userRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
