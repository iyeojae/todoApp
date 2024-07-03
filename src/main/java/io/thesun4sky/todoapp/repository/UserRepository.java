package io.thesun4sky.todoapp.repository;

import io.thesun4sky.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String username);
}