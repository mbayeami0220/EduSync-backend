package com.example.unchkbackend.service;

import com.example.unchkbackend.model.Etudiant;
import com.example.unchkbackend.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.unchkbackend.dto.StudentDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired private EtudiantRepository etudiantRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    
     public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
public Optional<Etudiant> getEtudiantById(String ine) {
    return etudiantRepository.findById(ine);
}
 public StudentDTO getStudentInfo() {
        StudentDTO student = new StudentDTO();
        student.setNom("Test");
        student.setPrenom("Utilisateur");
        student.setIne("INE0001");
        return student;
    }

public void deleteEtudiant(String ine) {
    etudiantRepository.deleteById(ine);
}

    public Etudiant createEtudiant(Etudiant etudiant) {
        if (etudiant.getPassword() != null) {
            etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));  // Encodage du mot de passe
        }
        return etudiantRepository.save(etudiant);
    }

    public boolean authenticate(String ine, String password) {
        Optional<Etudiant> optional = etudiantRepository.findByIne(ine);
        return optional
                .map(e -> passwordEncoder.matches(password, e.getPassword()))
                .orElse(false);
    }
     public List<StudentDTO> getAllStudents() {
        return new ArrayList<>();
     }

    public void saveEtudiant(String ine, String password) {
        Etudiant e = new Etudiant();
        e.setIne(ine);
        e.setPassword(passwordEncoder.encode(password));
        etudiantRepository.save(e);
    }
}
