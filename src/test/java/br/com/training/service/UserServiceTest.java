package br.com.training.service;

import br.com.training.dto.converter.UserConverterDTO;
import br.com.training.dto.request.UserRequestDTO;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserConverterDTO userConverterDTO;

    @InjectMocks
    private UserService userService;

    private User userToFind;
    private User userNull;
    private UserRequestDTO userToUpdate;
    private UserRequestDTO userToSave;
    private LocalDate date = LocalDate.parse("1991-06-06");

    @BeforeEach
    public void setUp() {
        userNull = new User();
        userNull = null;

        userToFind = new User();
        userToFind.setId(1L);
        userToFind.setName("Name");
        userToFind.setCpf("60922599580");
        userToFind.setEmail("email@email");
        userToFind.setBirthDate(date);

        this.userToSave = new UserRequestDTO("Name", "email@email", "60922599580", date);
        this.userToUpdate = new UserRequestDTO("Name Updated", "email@email", "60922599580", date);
    }

    @Test
    @DisplayName("Sucesso se o metodo FIND for chamado")
    public void testFindByCpf() {
        when(userRepositoryMock.findByCpf("60922599580")).thenReturn(userToFind);
        userService.findByCpf("60922599580");
        verify(userRepositoryMock, times(1)).findByCpf(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Sucesso se metodo SAVE for chamado")
    public void testSave() {
        userService.insert(userToSave);
        verify(userRepositoryMock, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Sucesso se buscar e ATUALIZAR")
    public void testUpdate() {
        when(userRepositoryMock.findByCpf("60922599580")).thenReturn(userToFind);
        userService.update(userToUpdate, "60922599580");
        verify(userRepositoryMock, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Sucesso se o metedo DELETE for chamado")
    public void testDelete() {
        when(userRepositoryMock.findByCpf("60922599580")).thenReturn(userToFind);
        userService.remove("60922599580");
        verify(userRepositoryMock, times(1)).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Sucesso se o USER não for null")
    public void testIfUserIsNull() {
        userService.checkUserIsNull(userToFind);
    }

    @Test
    @DisplayName("Sucesso se o USER for null")
    public void testIfUserIsNotNull() {
        userService.checkUserIsNotNull(userNull);
    }
}