package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.dto.request.UserRequestDTO;
import br.com.training.dto.response.UserResponseDTO;
import br.com.training.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
@Api(value = "API REST Users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{cpf}")
    @ApiOperation(value = "Retorna um usuario único pelo CPF")
    public ResponseEntity getUser(@PathVariable String cpf) {
        return new ResponseEntity<>(userService.findByCpf(cpf), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Salva um usuario")
    public ResponseEntity createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.insert(userRequestDTO),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cpf}")
    @ApiOperation(value = "Atualiza um usuario")
    public ResponseEntity<Void> updateUser(@PathVariable String cpf, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.update(userRequestDTO, cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cpf}")
    @ApiOperation(value = "Deleta um usuario pelo CPF")
    public ResponseEntity<Void> deleteUser(@PathVariable String cpf) {
        userService.remove(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}