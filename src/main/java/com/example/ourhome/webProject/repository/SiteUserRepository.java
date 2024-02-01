package com.example.ourhome.webProject.repository;

import com.example.ourhome.webProject.model.SiteUser;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {

    Optional<SiteUser> findByUsername(String username);
    Optional<SiteUser> findByUserid(String  userId);

}
