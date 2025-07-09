package com.potodev.NapRoute.repository;

import com.potodev.NapRoute.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
