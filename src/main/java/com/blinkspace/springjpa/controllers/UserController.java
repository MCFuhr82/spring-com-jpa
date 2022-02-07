package com.blinkspace.springjpa.controllers;

import com.blinkspace.springjpa.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController simplesmente retorna o objeto e os dados do objeto são gravados diretamente na resposta HTTP como JSON ou XML.
@RequestMapping(value = "/users") // RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
public class UserController {

    @GetMapping //para dizer que é o método GET do HTTP
    public ResponseEntity<User> findAll() {
        User user = new User(1, "Marcelo", "marcelinhofuhr@gmail.com", "45991011615", "123456");
        return ResponseEntity.ok().body(user);
    }
}
