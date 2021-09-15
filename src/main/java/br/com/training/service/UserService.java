package br.com.training.service;

import br.com.training.dto.converter.UserConverterDTO;
import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverterDTO userConverterDTO;

    public UserResponseDTO findCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        return userConverterDTO.entityToResponse(user);
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
        User user = userRepository.findByCpf(cpf);
        userRepository.delete(user);
    }

    public void update(UserRequestDTO userRequestDTO, String cpf) {
        User id = userRepository.findByCpf(cpf);
        User user = userConverterDTO.requestToEntity(userRequestDTO);
        user.setId(id.getId());
        userRepository.save(user);
    }
}
