package br.com.training.dto.response;

import br.com.training.model.User;

public class UserResponseDTO {

    private String name;
    private String email;
    private String cpf;

    public UserResponseDTO (User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
    }

    public UserResponseDTO() {
    }

    public UserResponseDTO(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
