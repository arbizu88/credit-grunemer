package com.grunemer.crm.service;

import com.grunemer.crm.dto.Profile;
import com.grunemer.crm.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile saveProfile(Profile profile) {
        Profile newProfile = profileRepository.save(profile);

        log.info("Profile saved!");

        return newProfile;
    }

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public void remove(Profile profile) {
        profileRepository.delete(profile);
        log.info("Profile Deleted - Id: {}", profile.getId());
    }
}
