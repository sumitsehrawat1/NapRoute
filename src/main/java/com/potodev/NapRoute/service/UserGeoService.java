package com.potodev.NapRoute.service;

import com.potodev.NapRoute.dto.GeoProximityResponse;

public interface UserGeoService {
    GeoProximityResponse getGeoProximity(double latitude, double longitude);
}
