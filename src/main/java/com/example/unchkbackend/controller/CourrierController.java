package com.example.unchkbackend.controller;

import com.example.unchkbackend.model.Courrier;
import com.example.unchkbackend.service.CourrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courriers")
@CrossOrigin(origins = "http://localhost:4200")
public class CourrierController {

    @Autowired
    private CourrierService courrierService;

    @PostMapping
    public ResponseEntity<Courrier> ajouterCourrier(
            @RequestParam String type,
            @RequestParam String objet,
            @RequestParam String date_reception,
            @RequestParam String expediteur,
            @RequestParam String destinataire,
            @RequestParam(value = "fichier", required = false) MultipartFile fichier
    ) throws IOException {

        String nomFichier = (fichier != null && !fichier.isEmpty()) ? fichier.getOriginalFilename() : null;

        Courrier courrier = Courrier.builder()
                .type(type)
                .objet(objet)
               .date(LocalDate.now())
                .expediteur(expediteur)
                .destinataire(destinataire)
                .nomFichier(nomFichier)
                .build();

        return ResponseEntity.ok(courrierService.ajouterCourrier(courrier));
    }

   
    @GetMapping
public ResponseEntity<List<Courrier>> getAllCourriers() {
    List<Courrier> courriers = courrierService.getAllCourriers();
    return ResponseEntity.ok(courriers);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCourrier(@PathVariable Long id) {
        courrierService.supprimerCourrier(id);
        return ResponseEntity.noContent().build();
    }
}
