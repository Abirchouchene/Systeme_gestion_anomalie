package tn.itbs.maintenance.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.itbs.maintenance.entity.Technicien;
import tn.itbs.maintenance.repositories.TechnicienRepository;

import java.util.List;
@RestController
@RequestMapping("/api/v1/maintenance/techniciens")
@RequiredArgsConstructor
@Tag(name = "Techniciens", description = "API de gestion des techniciens")

public class TechnicienController {

    private final TechnicienRepository technicienRepository;

    @PostMapping
    public ResponseEntity<Technicien> creerTechnicien(@RequestBody Technicien technicien) {
        return ResponseEntity.ok(technicienRepository.save(technicien));
    }

    @GetMapping
    public ResponseEntity<List<Technicien>> obtenirTousLesTechniciens() {
        return ResponseEntity.ok(technicienRepository.findAll());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Technicien>> obtenirTechniciensDisponibles() {
        return ResponseEntity.ok(technicienRepository.findByDisponibilite(true));
    }
}
