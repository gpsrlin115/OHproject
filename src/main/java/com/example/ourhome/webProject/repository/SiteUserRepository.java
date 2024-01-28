package com.example.ourhome.webProject.repository;

import com.example.ourhome.webProject.model.SiteUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {

    Optional<SiteUser> findByUsername(String username);
}
