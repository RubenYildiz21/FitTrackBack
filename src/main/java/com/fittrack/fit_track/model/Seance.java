package com.fittrack.fit_track.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@NamedEntityGraph(
    name = "Seance.blocs.series",
    attributeNodes = @NamedAttributeNode(value = "blocs", subgraph = "blocsSeries"),
    subgraphs = @NamedSubgraph(name = "blocsSeries", attributeNodes = @NamedAttributeNode("series"))
)
@Data
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeance;

    private LocalDateTime dateSeance;

    private String nameSeance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bloc> blocs;

    // Getters and Setters

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public LocalDateTime getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(LocalDateTime dateSeance) {
        this.dateSeance = dateSeance;
    }

    public Set<Bloc> getBlocs() {
        return blocs;
    }

    public void setBlocs(Set<Bloc> blocs) {
        this.blocs = blocs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNameSeance() {
        return nameSeance;
    }

    public void setNameSeance(String nameSeance) {
        this.nameSeance = nameSeance;
    }
}