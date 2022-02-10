package com.blinkspace.springjpa.services;

import com.blinkspace.springjpa.entities.User;
import com.blinkspace.springjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // tem que registrar como componente do springframework, para que consiga fazer a injeção de dependência em outra classe. Poderia ter colocado tb @Component
public class UserService {

    @Autowired //injeção de dependência para a camada de UserRepository
    private UserRepository repository;

    //método para retornar uma lista de usuários. Joga para a camada userRepository
    public List<User> findAll() {
        return repository.findAll();
    }

    // método para retonar um usuário pelo número do Id
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.get();
    }

    // método para inserir um usuário na base de dados
    public User insert(User user) { //passando um usuário como argumento
        return repository.save(user); //o método save já retorna o usuário salvo
    }

    // método para deletar um usuário na base de dados
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public User update(Integer id, User user) {
        User entity = repository.getOne(id);
        updateData(entity, user);
        return repository.save(entity);
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
