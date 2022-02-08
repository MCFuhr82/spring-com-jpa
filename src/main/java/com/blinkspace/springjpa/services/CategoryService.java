package com.blinkspace.springjpa.services;

import com.blinkspace.springjpa.entities.Category;
import com.blinkspace.springjpa.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // tem que registrar como componente do springframework, para que consiga fazer a injeção de dependência em outra classe. Poderia ter colocado tb @Component
public class CategoryService {

    @Autowired //injeção de dependência para a camada de CategoryRepository
    private CategoryRepository repository;

    //método para retornar uma lista de categorias. Joga para a camada CategoryRepository
    public List<Category> findAll() {
        return repository.findAll();
    }

    // método para retonar uma categoria pelo número do Id
    public Category findById(Integer id) {
        Optional<Category> category = repository.findById(id);
        return category.get();
    }
}
