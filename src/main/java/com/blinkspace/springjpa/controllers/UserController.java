package com.blinkspace.springjpa.controllers;

import com.blinkspace.springjpa.entities.User;
import com.blinkspace.springjpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@RestController simplesmente retorna o objeto e os dados do objeto são gravados diretamente na resposta HTTP como JSON ou XML.
@RequestMapping(value = "/users") // RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
public class UserController {

    @Autowired //injeção de dependência para a camada UserService
    private UserService userService;

    @GetMapping //para dizer que é o método GET do HTTP
    public ResponseEntity<List<User>> findAll() { //retornará uma lista de usuários
        List<User> list = userService.findAll(); // buscará as informações na camada userService, e não na camada UserRepository
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") // para buscar com o número do Id. As {} servem para informar o campo a ser buscado, que casa com o @PAthVariable
    public ResponseEntity<User> findById(@PathVariable Integer id) { // esse id é o informado no valor da busca
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
}
