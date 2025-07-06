package com.potodev.NapRoute.model;

import com.potodev.NapRoute.enums.UserGeoTagStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_tags")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGeoTag {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private GeoTag geoTag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserGeoTagStatus status;

    private Instant triggeredAt;

    @Column(nullable = false, updatable = false)
    private Instant addedAt = Instant.now();
}

