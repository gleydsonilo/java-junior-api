package br.com.training.service;

import br.com.training.dto.converter.UserConverterDTO;
import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverterDTO userConverterDTO;

    public UserResponseDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        checkUserIsNull(user);
        return userConverterDTO.modelToResponse(user);
    }

    public UserResponseDTO insert(UserRequestDTO userRequestDTO) {
        User userToInsert = userRepository.findByCpf(userRequestDTO.getCpf());
        checkUserIsNotNull(userToInsert);
        userToInsert = userConverterDTO.requestToModel(userRequestDTO);
        userRepository.save(userToInsert);
        return userConverterDTO.modelToResponse(userToInsert);
    }

    public void update(UserRequestDTO userRequestDTO, String cpf) {
        User userUpdated = userRepository.findByCpf(cpf);
        checkUserIsNull(userUpdated);
        if (userRequestDTO.getName() != null) {
            userUpdated.setName(userRequestDTO.getName());
        }

        if (userRequestDTO.getCpf() != null) {
            userUpdated.setCpf(userRequestDTO.getCpf());
        }

        if (userRequestDTO.getEmail() != null){
            userUpdated.setEmail(userRequestDTO.getEmail());
        }

        if (userRequestDTO.getBirthDate() != null) {
            userUpdated.setBirthDate(userRequestDTO.getBirthDate());
        }
        userRepository.save(userUpdated);
    }

    public void remove(String cpf) {
        User user = userRepository.findByCpf(cpf);
        checkUserIsNull(user);
        userRepository.delete(user);
    }

    public void checkUserIsNull(User user){
        if (user == null){
            throw new InvalidArgumentException("Usu??rio n??o localizado!");
        }
    }

    public void checkUserIsNotNull(User user){
        if (user != null){
            throw new InvalidArgumentException("Usu??rio j?? cadastrado!");
        }
    }
}
