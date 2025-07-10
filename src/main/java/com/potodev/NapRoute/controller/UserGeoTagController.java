package com.potodev.NapRoute.controller;

import com.potodev.NapRoute.dto.UserGeoTagRequest;
import com.potodev.NapRoute.dto.UserGeoTagResponse;
import com.potodev.NapRoute.service.UserGeoTagService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/geo-tag")
@RequiredArgsConstructor
public class UserGeoTagController {

    private final UserGeoTagService userGeoTagService;

    @PostMapping
    public ResponseEntity<UserGeoTagResponse> create(@RequestBody @Valid UserGeoTagRequest request) {
        UserGeoTagResponse created = userGeoTagService.addUserGeoTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGeoTagResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(UserGeoTagResponse.from(userGeoTagService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGeoTagResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UserGeoTagRequest request
    ) {
        return ResponseEntity.ok(userGeoTagService.update(id, request));
    }

    @GetMapping("")
    public ResponseEntity<List<UserGeoTagResponse>> getAllTags() {
        return ResponseEntity.ok(userGeoTagService.getUserGeoTags());
    }
}
