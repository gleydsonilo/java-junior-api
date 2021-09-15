package br.com.training.dto.converter;

import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterDTO {

    public UserRequestDTO entityToRequest (User user){

        UserRequestDTO dto = new UserRequestDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());
        return dto;
    }

    public User requestToEntity (UserRequestDTO userRequestDTO){

        User user = new User();
        user.setId(userRequestDTO.getId());
        user.setName(userRequestDTO.getName());
        user.setCpf(userRequestDTO.getCpf());
        user.setEmail(userRequestDTO.getEmail());
        user.setBirthDate(userRequestDTO.getBirthDate());
        return user;
    }

    public UserResponseDTO entityToResponse (User user){

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());
        return dto;
    }

    public User responseToEntity (UserResponseDTO userResponseDTO){

        User user = new User();
        user.setId(userResponseDTO.getId());
        user.setName(userResponseDTO.getName());
        user.setCpf(userResponseDTO.getCpf());
        user.setEmail(userResponseDTO.getEmail());
        user.setBirthDate(userResponseDTO.getBirthDate());
        return user;
    }
}
