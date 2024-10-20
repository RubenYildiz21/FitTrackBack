package com.fittrack.fit_track.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode pour rechercher un utilisateur par email
    Optional<User> findByEmail(String email);
}