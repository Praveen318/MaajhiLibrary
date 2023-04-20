package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByToken(String token);

	Optional<User> findByEmail(String email);

}
