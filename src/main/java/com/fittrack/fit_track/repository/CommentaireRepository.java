package com.fittrack.fit_track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fittrack.fit_track.model.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}