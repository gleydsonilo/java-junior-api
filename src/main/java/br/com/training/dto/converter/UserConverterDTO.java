package br.com.training.dto.converter;

import br.com.training.dto.request.UserRequestDTO;
import br.com.training.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterDTO {

    public UserRequestDTO entityToRequest (User user){

        UserRequestDTO dto = new UserRequestDTO();
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());
        return dto;
    }

    public User requestToEntity (UserRequestDTO userRequestDTO){

        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setCpf(userRequestDTO.getCpf());
        user.setEmail(userRequestDTO.getEmail());
        user.setBirthDate(userRequestDTO.getBirthDate());
        return user;
    }
}
