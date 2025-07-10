package com.potodev.NapRoute.service;

import com.potodev.NapRoute.dto.GeoTagRequest;
import com.potodev.NapRoute.model.GeoTag;
import java.util.UUID;

public interface GeoTagService {
    GeoTag createGeoTag(GeoTagRequest geoTagRequest);
    GeoTag updateGeoTag(UUID id, GeoTagRequest geoTagRequest);
    GeoTag getById(UUID id);
}
