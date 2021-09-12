package br.com.training.service;

import br.com.training.dto.converter.UserConverterDTO;
import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.DataIntegrityException;
import br.com.training.service.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverterDTO userConverterDTO;

    public User findCpf(String cpf) {
        Optional<User> user = Optional.ofNullable(userRepository.findByCpf(cpf));
        return user.orElseThrow(() -> new InvalidArgumentException("CPF não localizado!"));
    }

    public UserRequestDTO insert(UserRequestDTO userRequestDTO) {
        User user = userConverterDTO.requestToEntity(userRequestDTO);
        try {
            return userConverterDTO.entityToRequest(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("CPF ou E-Mail já cadastrado!");
        }
    }

    public void remove(String cpf) {
        userRepository.delete(findCpf(cpf));
    }

    public void update(String cpf, UserRequestDTO userRequestDTO) {
        User user = userConverterDTO.requestToEntity(userRequestDTO);
        remove(cpf);
        userRepository.save(user);
    }
}
