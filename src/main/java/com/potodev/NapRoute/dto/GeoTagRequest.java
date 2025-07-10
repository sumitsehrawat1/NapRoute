package com.potodev.NapRoute.dto;

import com.potodev.NapRoute.enums.RadiusUnits;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoTagRequest {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Double radius;
    @NotNull
    private RadiusUnits radiusUnits;
    private String label;

    public static GeoTagRequest from(UserGeoTagRequest userGeoTagRequest) {
        return GeoTagRequest.builder()
                .latitude(userGeoTagRequest.getLatitude())
                .longitude(userGeoTagRequest.getLongitude())
                .radius(userGeoTagRequest.getRadius())
                .label(userGeoTagRequest.getLabel())
                .radiusUnits(userGeoTagRequest.getRadiusUnits())
                .build();
    }
}
