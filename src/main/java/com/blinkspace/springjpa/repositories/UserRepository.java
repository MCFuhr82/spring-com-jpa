package com.blinkspace.springjpa.repositories;

import com.blinkspace.springjpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//não precisa colocar @Repository nesse caso, pq já esta herdadndo da interface do JpaRepository, que já tem a anotação
public interface UserRepository extends JpaRepository<User, Integer> {
}
