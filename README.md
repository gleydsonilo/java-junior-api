# java-training-api

## DESAFIOS

[DESAFIOS.md](https://github.com/GuillaumeFalourd/java-training-api/tree/main/DESAFIOS.md)

- ### Primeira Parte
- [x] Versionando seu código
- [x] Camada de Service
- [x] CRUD completo

- ### Segunda Parte
- [x] Uso de DTO
- [ ] Uso de anotações de validações


## Requisitos

- Maven
- Spring
- Java 11
- Hibernate
- JPA

## Endpoint disponíveis (v1)

### Criar usuário

**POST:** `http://localhost:8080/users` com *body*:

```json
{
    "name":"exemplo",
    "cpf":"123456789",
    "email":"email@email",
    "birthDate":"1900-01-01"
}
```

### Obter usuário com CPF

**GET:** `http://localhost:8080/users/{cpf}` vai retornar:

```json
{
    "id": 1,
    "name": "exemplo",
    "email": "email@email",
    "cpf": "123456789",
    "birthDate": "1900-01-01"
}
```

### Atualiza usuário com CPF

**PUT:** `http://localhost:8080/users/{cpf}` 


### Detela usuário com CPF

**DELETE:** `http://localhost:8080/users/{cpf}`
