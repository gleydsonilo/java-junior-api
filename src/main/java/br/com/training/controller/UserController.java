package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.training.model.User;

import java.util.Optional;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid User user) {
		return userService.insert(user);
	}

	@GetMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
    public Optional<User> getUser (@PathVariable String cpf){
        return userService.findByCpf(cpf);
    }

}
