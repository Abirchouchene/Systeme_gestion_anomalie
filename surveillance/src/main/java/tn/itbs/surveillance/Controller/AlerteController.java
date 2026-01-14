package tn.itbs.surveillance.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.itbs.surveillance.entity.Alerte;
import tn.itbs.surveillance.repositories.AlerteRepository;
import tn.itbs.surveillance.service.AlerteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveillance/alertes")
@RequiredArgsConstructor
@Tag(name = "Alertes", description = "API de gestion des alertes")
public class AlerteController {

    private final AlerteService alerteService;

    @PostMapping
    @Operation(summary = "Créer une nouvelle alerte")
    public ResponseEntity<Alerte> creerAlerte(@RequestBody Alerte alerte) {
        Alerte nouvelleAlerte = alerteService.creerAlerte(alerte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleAlerte);
    }

    @GetMapping
    @Operation(summary = "Obtenir toutes les alertes")
    public ResponseEntity<List<Alerte>> obtenirToutesLesAlertes() {
        return ResponseEntity.ok(alerteService.obtenirToutesLesAlertes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une alerte par ID")
    public ResponseEntity<Alerte> obtenirAlerte(@PathVariable Long id) {
        return ResponseEntity.ok(alerteService.obtenirAlerteParId(id));
    }

    @GetMapping("/gravite/{gravite}")
    @Operation(summary = "Obtenir les alertes par niveau de gravité")
    public ResponseEntity<List<Alerte>> obtenirAlertesParGravite(@PathVariable String gravite) {
        return ResponseEntity.ok(alerteService.obtenirAlertesParGravite(gravite));
    }

    @GetMapping("/recentes")
    @Operation(summary = "Obtenir les alertes récentes (dernières 24h)")
    public ResponseEntity<List<Alerte>> obtenirAlertesRecentes(@RequestParam(defaultValue = "24") int heures) {
        return ResponseEntity.ok(alerteService.obtenirAlertesRecentes(heures));
    }
}