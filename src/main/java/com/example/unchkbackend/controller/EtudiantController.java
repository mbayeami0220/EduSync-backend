package com.example.unchkbackend.controller;
import org.springframework.http.ResponseEntity;
import com.example.unchkbackend.repository.EtudiantRepository;
import com.example.unchkbackend.model.Etudiant;


import com.example.unchkbackend.dto.StudentDTO;

// Pour les contrôleurs Spring
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.unchkbackend.model.Role;


// Pour l'injection de dépendances
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Pour les listes et optionnels
import java.util.List;
import java.util.Optional;

import com.example.unchkbackend.model.Etudiant;
import com.example.unchkbackend.service.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "http://localhost:4200") 
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // Récupérer un étudiant par son ID (INE)
    @GetMapping("/{ine}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable String ine) {
        return etudiantService.getEtudiantById(ine)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Récupérer la liste de tous les étudiants
    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
         
    }

    // Récupérer les informations supplémentaires d'un étudiant (par exemple, un DTO)
    @GetMapping("/info")
    public ResponseEntity<StudentDTO> getStudentInfo() {
        StudentDTO dto = etudiantService.getStudentInfo(); 
        return ResponseEntity.ok(dto);
    }

    // Supprimer un étudiant par son ID (INE)
    @DeleteMapping("/{ine}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable String ine) {
        etudiantService.deleteEtudiant(ine);
        return ResponseEntity.noContent().build();
    }

    // Ajouter un nouvel étudiant
 @PostMapping
public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
    etudiant.setRole(Role.ETUDIANT);
    Etudiant savedEtudiant = etudiantService.createEtudiant(etudiant);
    return ResponseEntity.status(201).body(savedEtudiant); // HTTP 201 + JSON
}


}
