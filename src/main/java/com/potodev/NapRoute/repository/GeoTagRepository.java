package com.potodev.NapRoute.repository;

import com.potodev.NapRoute.model.GeoTag;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoTagRepository extends JpaRepository<GeoTag, UUID> {
}
