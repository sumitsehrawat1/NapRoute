package com.potodev.NapRoute.service.impl;

import static com.potodev.NapRoute.exceptions.NapErrorCode.GEOTAG_NOT_FOUND;

import com.potodev.NapRoute.dto.GeoTagRequest;
import com.potodev.NapRoute.enums.UserGeoTagStatus;
import com.potodev.NapRoute.exceptions.NapErrorCode;
import com.potodev.NapRoute.exceptions.NapException;
import com.potodev.NapRoute.model.GeoTag;
import com.potodev.NapRoute.model.UserGeoTag;
import com.potodev.NapRoute.repository.GeoTagRepository;
import com.potodev.NapRoute.service.GeoTagService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GeoTagServiceImpl implements GeoTagService {

    GeoTagRepository geoTagRepository;

    @Override
    public GeoTag createGeoTag(GeoTagRequest geoTagRequest) {
        GeoTag geoTag = GeoTag.builder()
                .latitude(geoTagRequest.getLatitude())
                .longitude(geoTagRequest.getLongitude())
                .radius(geoTagRequest.getRadius())
                .radiusUnits(geoTagRequest.getRadiusUnits())
                .label(geoTagRequest.getLabel())
                .build();
        return geoTagRepository.save(geoTag);
    }

    @Override
    public GeoTag updateGeoTag(UUID id, GeoTagRequest geoTagRequest) {
        GeoTag geoTag = getById(id);

        geoTag.setLatitude(geoTagRequest.getLatitude());
        geoTag.setLongitude(geoTagRequest.getLongitude());
        geoTag.setRadius(geoTagRequest.getRadius());
        geoTag.setRadiusUnits(geoTagRequest.getRadiusUnits());
        geoTag.setLabel(geoTagRequest.getLabel());

        return geoTagRepository.save(geoTag);
    }

    @Override
    public GeoTag getById(UUID id) {
        return geoTagRepository.findById(id)
                .orElseThrow(() -> NapException.of(GEOTAG_NOT_FOUND));
    }
}
