package com.potodev.NapRoute.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "geo_tags")
@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class GeoTag {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double radius;

    private String label;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
