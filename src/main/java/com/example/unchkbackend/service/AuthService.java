package com.example.unchkbackend.service;

import com.example.unchkbackend.auth.AuthRequest;
import com.example.unchkbackend.auth.AuthResponse;
import com.example.unchkbackend.model.Role;
import com.example.unchkbackend.model.Admin;
import com.example.unchkbackend.model.Etudiant;
import com.example.unchkbackend.repository.AdminRepository;
import com.example.unchkbackend.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final EtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

   public AuthResponse login(AuthRequest request) {
    String identifier = request.getIdentifier();
    String password = request.getPassword();

    // Tentative de login en tant qu'Admin
    var adminOpt = adminRepository.findByUsername(identifier);
    if (adminOpt.isPresent()) {
        Admin admin = adminOpt.get();
        if (passwordEncoder.matches(password, admin.getPassword())) {
            String token = jwtService.generateToken(admin.getUsername(), Role.ADMIN.name());
            return new AuthResponse(token, Role.ADMIN.name());
        }
    }

    // Tentative de login en tant qu'Étudiant
    var etudiantOpt = etudiantRepository.findByIne(identifier);
    if (etudiantOpt.isPresent()) {
        Etudiant etudiant = etudiantOpt.get();
        if (passwordEncoder.matches(password, etudiant.getPassword())) {
            String token = jwtService.generateToken(etudiant.getIne(), Role.ETUDIANT.name());
            return new AuthResponse(token, Role.ETUDIANT.name());
        }
    }

    throw new BadCredentialsException("Identifiants incorrects");
}

}
