package com.potodev.NapRoute.dto;

import com.potodev.NapRoute.enums.RadiusUnits;
import com.potodev.NapRoute.enums.UserGeoTagStatus;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class UserGeoTagRequest {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Double radius;
    private String label;
    @NotNull
    private UserGeoTagStatus status;
    @NotNull
    private RadiusUnits radiusUnits;
}


