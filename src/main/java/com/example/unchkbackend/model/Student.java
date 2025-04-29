package com.example.unchkbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    private String ine;
    private String nom;
    private String prenom;
    private String formations;
    private String promo;
    private String diplome;
}
