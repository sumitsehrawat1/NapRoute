package com.potodev.NapRoute.service.impl;

import static com.potodev.NapRoute.util.ConversionUtil.convertMetersToUnit;
import static com.potodev.NapRoute.util.ConversionUtil.convertToMeters;
import static com.potodev.NapRoute.util.GeoUtils.calculateDistanceInMeters;
import static com.potodev.NapRoute.util.SecurityUtils.getCurrentUser;

import com.potodev.NapRoute.dto.GeoProximityResponse;
import com.potodev.NapRoute.enums.RadiusUnits;
import com.potodev.NapRoute.model.GeoTag;
import com.potodev.NapRoute.model.User;
import com.potodev.NapRoute.model.UserGeoTag;
import com.potodev.NapRoute.service.UserGeoService;
import com.potodev.NapRoute.service.UserGeoTagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserGeoServiceImpl implements UserGeoService {

    UserGeoTagService userGeoTagService;

    @Override
    public GeoProximityResponse getGeoProximity(double latitude, double longitude) {
        User user = getCurrentUser();
        UserGeoTag activeTag = userGeoTagService.getActiveGeoTag(user);
        GeoTag geoTag = activeTag.getGeoTag();

        double distanceInMeters = calculateDistanceInMeters(latitude, longitude,
                geoTag.getLatitude(), geoTag.getLongitude());

        RadiusUnits radiusUnits = geoTag.getRadiusUnits();
        double radialDistanceToTarget = convertMetersToUnit(distanceInMeters, radiusUnits);

        double geoTagRadiusInMeters = convertToMeters(geoTag.getRadius(), radiusUnits);

        boolean triggerAlarm = distanceInMeters <= geoTagRadiusInMeters;

        return GeoProximityResponse.builder()
                .radialDistanceToTarget(radialDistanceToTarget)
                .radialUnits(radiusUnits.name())
                .triggerAlarm(triggerAlarm)
                .build();
    }
}
