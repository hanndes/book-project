package com.handedereli.bookproject.controller;


import com.handedereli.bookproject.controller.dto.request.FavoriteRequest;
import com.handedereli.bookproject.controller.dto.response.FavoriteResponse;
import com.handedereli.bookproject.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;


    @PostMapping
    public ResponseEntity<FavoriteResponse> addFavorite(@Valid @RequestBody FavoriteRequest favoriteRequest) {
        FavoriteResponse created = favoriteService.addFavorite(favoriteRequest);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteResponse>> getFavoritesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUser(userId));
    }
}
