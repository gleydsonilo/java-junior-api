package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.dto.converter.UserConverterDTO;
import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.training.model.User;

import java.util.Map;
import java.util.Optional;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserRequestDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
		return (userService.insert(userRequestDTO));
	}

	@GetMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUser (@PathVariable String cpf) {
        return new UserResponseDTO(userService.findCpf(cpf));
    }

    @PutMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser (@PathVariable String cpf, @RequestBody @Valid UserRequestDTO userRequestDTO){
		userService.update(cpf, userRequestDTO);
	}

	@DeleteMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser (@PathVariable String cpf){
		userService.remove(cpf);
	}
}