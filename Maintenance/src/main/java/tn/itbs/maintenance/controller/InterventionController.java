package tn.itbs.maintenance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.itbs.maintenance.dto.AlerteDTO;
import tn.itbs.maintenance.entity.Intervention;
import tn.itbs.maintenance.repositories.InterventionRepository;
import tn.itbs.maintenance.service.InterventionService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/maintenance/interventions")
@RequiredArgsConstructor
@Tag(name = "Interventions", description = "API de gestion des interventions")
public class InterventionController {

    private final InterventionService interventionService;

    @GetMapping
    @Operation(summary = "Obtenir toutes les interventions")
    public ResponseEntity<List<Intervention>> suivreInterventions() {
        return ResponseEntity.ok(interventionService.suivreInterventions());
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Obtenir les interventions par statut")
    public ResponseEntity<List<Intervention>> obtenirInterventionsParStatut(@PathVariable String statut) {
        return ResponseEntity.ok(interventionService.obtenirInterventionsParStatut(statut));
    }

    @PutMapping("/{id}/statut")
    @Operation(summary = "Mettre à jour le statut d'une intervention")
    public ResponseEntity<Intervention> mettreAJourStatut(
            @PathVariable Long id,
            @RequestParam String statut) {
        return ResponseEntity.ok(interventionService.mettreAJourStatut(id, statut));
    }

    @GetMapping("/alertes")
    @Operation(summary = "Récupérer toutes les alertes depuis Surveillance (Communication Synchrone)")
    public ResponseEntity<List<AlerteDTO>> recupererAlertesDepuisSurveillance() {
        return ResponseEntity.ok(interventionService.recupererToutesLesAlertes());
    }

    @GetMapping("/alertes/{alerteId}")
    @Operation(summary = "Récupérer une alerte spécifique depuis Surveillance (Communication Synchrone)")
    public ResponseEntity<AlerteDTO> recupererAlerteDepuisSurveillance(@PathVariable Long alerteId) {
        return ResponseEntity.ok(interventionService.recupererDetailsAlerte(alerteId));
    }
}