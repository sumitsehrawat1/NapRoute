package com.potodev.NapRoute.repository;

import com.potodev.NapRoute.enums.UserGeoTagStatus;
import com.potodev.NapRoute.model.User;
import com.potodev.NapRoute.model.UserGeoTag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGeoTagRepository extends JpaRepository<UserGeoTag, UUID> {
    boolean existsByUserAndStatus(User user, UserGeoTagStatus status);
    List<UserGeoTag> findAllByUser(User user);
    Optional<UserGeoTag> findByUserAndStatus(User user, UserGeoTagStatus status);
}
