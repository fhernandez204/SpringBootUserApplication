package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  //List<User> findByPublished(boolean published);

  Optional<User> findByEmail(String email);
}
