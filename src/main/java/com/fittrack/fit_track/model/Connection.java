package com.fittrack.fit_track.model;

import jakarta.persistence.*;

@Entity
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConnection; // Clé primaire auto-incrémentée

    @ManyToOne
    @JoinColumn(name = "idUserFollow")
    private User follow;  // Utilisateur se fait follow

    @ManyToOne
    @JoinColumn(name = "idUserFollower")
    private User follower;  // Utilisateur qui follow

    // Getters et setters
    public User getFollow() {
        return follow;
    }

    public void setFollow(User follow) {
        this.follow = follow;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
