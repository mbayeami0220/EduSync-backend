package com.example.unchkbackend.model;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    private String ine;

    private String nom;
    private String prenom;

    @JsonProperty("date_naissance")
    private String dateNaissance;

    private String formation;
    private String promo;

    @JsonProperty("annee_debut")
    private String anneeDebut;

    @JsonProperty("annee_sortie")
    private String anneeSortie;

    private String diplomes;

    @JsonProperty("autres_formations")
    private String autresFormations;
 @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
