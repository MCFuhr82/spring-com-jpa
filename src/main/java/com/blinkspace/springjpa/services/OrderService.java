package com.blinkspace.springjpa.services;

import com.blinkspace.springjpa.entities.Order;
import com.blinkspace.springjpa.entities.User;
import com.blinkspace.springjpa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // tem que registrar como componente do springframework, para que consiga fazer a injeção de dependência em outra classe. Poderia ter colocado tb @Component
public class OrderService {

    @Autowired //injeção de dependência para a camada de OrderRepository
    private OrderRepository repository;

    //método para retornar uma lista de usuários. Joga para a camada OrderRepository
    public List<Order> findAll() {
        return repository.findAll();
    }

    // método para retonar um pedido pelo número do Id
    public Order findById(Integer id) {
        Optional<Order> order = repository.findById(id);
        return order.get();
    }
}
