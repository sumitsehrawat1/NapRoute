package com.potodev.NapRoute.dto;

import com.potodev.NapRoute.model.UserGeoTag;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGeoTagResponse {
    private UUID userGeoTagId;
    private UUID geoTagId;
    private Instant triggeredAt;
    private Instant lastUpdatedAt;
    private String status;

    public static UserGeoTagResponse from(UserGeoTag userGeoTag) {
        return UserGeoTagResponse.builder()
                .userGeoTagId(userGeoTag.getId())
                .geoTagId(userGeoTag.getGeoTag().getId())
                .triggeredAt(userGeoTag.getAddedAt())
                .lastUpdatedAt(userGeoTag.getUpdatedAt())
                .status(userGeoTag.getStatus().name())
                .build();
    }
}