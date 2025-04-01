package com.grunemer.crm.controller;

import com.grunemer.crm.dto.Profile;
import com.grunemer.crm.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Profile getProfile(@PathVariable Long id) {
        return profileService.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        if (Objects.isNull(id) || !Objects.equals(id, profile.getId())) {
            throw new IllegalArgumentException("Id must be provided");
        }
        return profileService.saveProfile(profile);
    }

    @DeleteMapping(value = "/{id}")
    public void updateProfile(@PathVariable Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id must be provided");
        } else {
            Profile profile = profileService.findById(id)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            profileService.remove(profile);
        }
    }

    @PostMapping
    public Profile saveProfile(@RequestBody Profile profile) {
        return profileService.saveProfile(profile);
    }


}
