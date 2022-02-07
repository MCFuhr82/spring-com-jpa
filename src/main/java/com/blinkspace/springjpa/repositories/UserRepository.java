package com.blinkspace.springjpa.repositories;

import com.blinkspace.springjpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
