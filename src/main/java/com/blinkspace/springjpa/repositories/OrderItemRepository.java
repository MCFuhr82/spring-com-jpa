package com.blinkspace.springjpa.repositories;

import com.blinkspace.springjpa.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

//não precisa colocar @Repository nesse caso, pq já esta herdadndo da interface do JpaRepository, que já tem a anotação
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
