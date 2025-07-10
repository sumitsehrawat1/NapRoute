package com.potodev.NapRoute.service.impl;

import static com.potodev.NapRoute.exceptions.NapErrorCode.ACTIVE_GEO_TAG_PRESENT;
import static com.potodev.NapRoute.exceptions.NapErrorCode.USERTAG_NOT_FOUND;
import static com.potodev.NapRoute.util.SecurityUtils.getCurrentUser;

import com.potodev.NapRoute.dto.GeoTagRequest;
import com.potodev.NapRoute.dto.UserGeoTagRequest;
import com.potodev.NapRoute.dto.UserGeoTagResponse;
import com.potodev.NapRoute.enums.UserGeoTagStatus;
import com.potodev.NapRoute.exceptions.NapException;
import com.potodev.NapRoute.model.GeoTag;
import com.potodev.NapRoute.model.User;
import com.potodev.NapRoute.model.UserGeoTag;
import com.potodev.NapRoute.repository.UserGeoTagRepository;
import com.potodev.NapRoute.service.GeoTagService;
import com.potodev.NapRoute.service.UserGeoTagService;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserGeoTagServiceImpl implements UserGeoTagService {

    UserGeoTagRepository userGeoTagRepository;
    GeoTagService geoTagService;

    @Override
    public UserGeoTag getById(UUID id) {
        return userGeoTagRepository.findById(id)
                .orElseThrow(() -> NapException.of(USERTAG_NOT_FOUND));
    }

    @Override
    public UserGeoTagResponse update(UUID id, UserGeoTagRequest request) {
        UserGeoTag userGeoTag = getById(id);
        userGeoTag.setStatus(request.getStatus());
        User user = getCurrentUser();

        if(request.getStatus().equals(UserGeoTagStatus.ACTIVE) &&
                userGeoTagRepository.existsByUserAndStatus(user, UserGeoTagStatus.ACTIVE)) {
            throw NapException.of(ACTIVE_GEO_TAG_PRESENT);
        }

        return UserGeoTagResponse.from(userGeoTagRepository.save(userGeoTag));
    }

    @Override
    public UserGeoTagResponse addUserGeoTag(UserGeoTagRequest request) {
        User user = getCurrentUser();

        if(userGeoTagRepository.existsByUserAndStatus(user, UserGeoTagStatus.ACTIVE)){
            throw NapException.of(ACTIVE_GEO_TAG_PRESENT);
        }

        GeoTag geoTag = geoTagService.createGeoTag(GeoTagRequest.from(request));

        UserGeoTag userGeoTag = UserGeoTag.builder()
                .user(user)
                .geoTag(geoTag)
                .status(UserGeoTagStatus.ACTIVE)
                .build();
        userGeoTag = userGeoTagRepository.save(userGeoTag);

        return UserGeoTagResponse.from(userGeoTag);
    }

    @Override
    public List<UserGeoTagResponse> getUserGeoTags() {
        User user = getCurrentUser();
        return userGeoTagRepository.findAllByUser(user)
                .stream().map(UserGeoTagResponse::from)
                .toList();
    }

    @Override
    public UserGeoTag getActiveGeoTag(User user) {
        return userGeoTagRepository.findByUserAndStatus(user, UserGeoTagStatus.ACTIVE)
                .orElseThrow(() -> NapException.of(USERTAG_NOT_FOUND));
    }
}
