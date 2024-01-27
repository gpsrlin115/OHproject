package com.example.ourhome.webProject.repository;

import com.example.ourhome.webProject.model.SiteUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SiteUserRepository {

    private final EntityManager em;

    public void save(SiteUser siteUser){
        em.persist(siteUser);
    }

    public SiteUser findOne(Long id){
        return em.find(SiteUser.class, id);
    }

    public List<SiteUser> findAll() {
        return em.createQuery("select m from SiteUser m", SiteUser.class)
                .getResultList();
    }

    public List<SiteUser> findByName(String username) {
        return em.createQuery("select m from SiteUser m where m.username = :username", SiteUser.class)
                .setParameter("username", username)
                .getResultList();
    }
}
