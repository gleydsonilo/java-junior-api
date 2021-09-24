package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{cpf}")
    public ResponseEntity getUser(@PathVariable String cpf) {
        return new ResponseEntity<>(userService.findByCpf(cpf), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.insert(userRequestDTO),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cpf}")
    public ResponseEntity<Void> updateUser(@PathVariable String cpf, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.update(userRequestDTO, cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable String cpf) {
        userService.remove(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}