package com.potodev.NapRoute.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "favourites")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavedGeoTag {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private GeoTag geoTag;

    private String tag;

    @Column(nullable = false)
    private int usageCount = 0;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

