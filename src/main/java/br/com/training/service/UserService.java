package br.com.training.service;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.DataIntegrityException;
import br.com.training.service.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByCpf(String cpf) {
        Optional<User> user = Optional.ofNullable(userRepository.findByCpf(cpf));
        return user.orElseThrow(() -> new InvalidArgumentException("CPF não localizado!"));
    }

    public User insert(User user){
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("CPF ou E-Mail já cadastrado!");
        }
    }
    public void remove(String cpf){
        userRepository.delete(findByCpf(cpf));
    }

    public void update(String cpf, User user){
        remove(cpf);
        userRepository.save(user);
    }
}
