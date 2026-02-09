package com.example.schoolmanagement.user.repository;

import com.example.schoolmanagement.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String username);
    boolean existsByEmail(String email);
    Optional<User> findByName(String username);
    boolean existsByNameIgnoreCase(String username);
    boolean existsByEmailIgnoreCase(String email);
    void deleteByName(String username);

}
