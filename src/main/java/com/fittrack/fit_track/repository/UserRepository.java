package com.fittrack.fit_track.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode pour rechercher un utilisateur par email
    Optional<User> findByEmail(String email);

    // Méthode pour rechercher des utilisateurs par prénom ou nom
    @Query("SELECT u.age, u.email, u.firstName, u.lastName, u.gender, u.goalWeight, u.height, u.id, u.mainGoal, u.place, u.profilePicture, u.roles, u.trainingLevel, u.weight " +
            "FROM User u " +
            "WHERE (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) AND LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
            "OR (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :lastName, '%')) AND LOWER(u.lastName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
            "OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<User> findWithSearch(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("query") String query);
}