package com.potodev.NapRoute.service;

import com.potodev.NapRoute.dto.UserGeoTagRequest;
import com.potodev.NapRoute.dto.UserGeoTagResponse;
import com.potodev.NapRoute.model.User;
import com.potodev.NapRoute.model.UserGeoTag;
import java.util.List;
import java.util.UUID;

public interface UserGeoTagService {
    UserGeoTag getById(UUID id);
    UserGeoTagResponse update(UUID id, UserGeoTagRequest request);
    UserGeoTagResponse addUserGeoTag(UserGeoTagRequest request);
    List<UserGeoTagResponse> getUserGeoTags();
    UserGeoTag getActiveGeoTag(User user);
}
