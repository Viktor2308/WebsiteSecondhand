package com.example.websitesecondhand.repository;

import com.example.websitesecondhand.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findUserByUsername(String userName);

    Boolean existsByUsername(String username);

}
