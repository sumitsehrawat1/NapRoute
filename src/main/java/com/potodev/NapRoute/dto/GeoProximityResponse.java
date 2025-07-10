package com.potodev.NapRoute.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoProximityResponse {
    private double radialDistanceToTarget;
    private String radialUnits;
    private boolean triggerAlarm;
}
