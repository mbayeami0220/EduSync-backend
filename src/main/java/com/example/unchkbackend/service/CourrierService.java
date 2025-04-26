package com.example.unchkbackend.service;

import com.example.unchkbackend.model.Courrier;
import com.example.unchkbackend.repository.CourrierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourrierService {

    private final CourrierRepository courrierRepository;

    public CourrierService(CourrierRepository courrierRepository) {
        this.courrierRepository = courrierRepository;
    }

    public Courrier ajouterCourrier(Courrier courrier) {
        return courrierRepository.save(courrier);
    }

    public List<Courrier> getAllCourriers() {
        return courrierRepository.findAll();
    }

    public void supprimerCourrier(Long id) {
        courrierRepository.deleteById(id);
    }
}
