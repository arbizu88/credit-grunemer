package com.grunemer.crm.repository;

import com.grunemer.crm.dto.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {}
