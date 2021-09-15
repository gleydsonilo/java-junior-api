package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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

    @GetMapping(value = "/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUser(@PathVariable String cpf) {
        return userService.findCpf(cpf);
    }

    @PutMapping(value = "/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable String cpf, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.update(userRequestDTO, cpf);
    }

    @DeleteMapping(value = "/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String cpf) {
        userService.remove(cpf);
    }
}