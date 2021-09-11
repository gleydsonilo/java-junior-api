package br.com.training.service;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByCpf(String cpf){
        return Optional.ofNullable(userRepository.findByCpf(cpf));
    }

    public User insert(User user){
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("CPF ou E-Mail já cadastrado!");
        }
    }

}
