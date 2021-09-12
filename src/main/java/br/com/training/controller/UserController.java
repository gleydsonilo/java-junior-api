package br.com.training.controller;

import javax.validation.Valid;

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
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		return ResponseEntity.ok(userService.insert(user));
	}

	@GetMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
   	public ResponseEntity<User> getUser (@PathVariable String cpf) {
        return ResponseEntity.ok(userService.findByCpf(cpf));
        }

        @PutMapping (value = "/{cpf}")
	public ResponseEntity<Void> updateUser (@PathVariable String cpf, @RequestBody @Valid User user){
		userService.update(cpf, user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping (value = "/{cpf}")
	public ResponseEntity<Void> deleteUser (@PathVariable String cpf){
		userService.remove(cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
