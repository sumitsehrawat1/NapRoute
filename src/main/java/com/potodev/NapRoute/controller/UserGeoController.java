package com.potodev.NapRoute.controller;

import com.potodev.NapRoute.dto.GeoProximityResponse;
import com.potodev.NapRoute.service.UserGeoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserGeoController {

    UserGeoService userGeoService;

    @GetMapping("/proximity")
    public ResponseEntity<GeoProximityResponse> getGeoProximityStatus(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return ResponseEntity.ok(userGeoService.getGeoProximity(lat, lon));
    }
}
