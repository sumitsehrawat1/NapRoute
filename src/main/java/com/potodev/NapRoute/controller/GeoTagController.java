package com.potodev.NapRoute.controller;

import com.potodev.NapRoute.dto.GeoTagRequest;
import com.potodev.NapRoute.model.GeoTag;
import com.potodev.NapRoute.service.GeoTagService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo-tag")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeoTagController {

    GeoTagService geoTagService;

    @PutMapping("/{id}")
    public ResponseEntity<GeoTag> updateGeoTag(
            @PathVariable UUID id,
            @Valid @RequestBody GeoTagRequest geoTagRequest) {
        GeoTag updatedGeoTag = geoTagService.updateGeoTag(id, geoTagRequest);
        return ResponseEntity.ok(updatedGeoTag);
    }
}

