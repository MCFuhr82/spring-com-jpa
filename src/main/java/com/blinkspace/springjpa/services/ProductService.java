package com.blinkspace.springjpa.services;

import com.blinkspace.springjpa.entities.Product;
import com.blinkspace.springjpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // tem que registrar como componente do springframework, para que consiga fazer a injeção de dependência em outra classe. Poderia ter colocado tb @Component
public class ProductService {

    @Autowired //injeção de dependência para a camada de ProductRepository
    private ProductRepository repository;

    //método para retornar uma lista de produtos. Joga para a camada ProductRepository
    public List<Product> findAll() {
        return repository.findAll();
    }

    // método para retonar um produto pelo número do Id
    public Product findById(Integer id) {
        Optional<Product> product = repository.findById(id);
        return product.get();
    }
}
