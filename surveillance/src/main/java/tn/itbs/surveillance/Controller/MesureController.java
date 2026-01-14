package tn.itbs.surveillance.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.itbs.surveillance.entity.MesureAnalyse;
import tn.itbs.surveillance.service.MesureAnalyseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveillance/mesures")
@RequiredArgsConstructor
@Tag(name = "Mesures", description = "API de gestion des mesures et analyses")
public class MesureController {

    private final MesureAnalyseService mesureService;

    @PostMapping
    @Operation(summary = "Ajouter une nouvelle mesure (détection automatique d'anomalies)")
    public ResponseEntity<MesureAnalyse> ajouterMesure(@RequestBody MesureAnalyse mesure) {
        MesureAnalyse nouvelleMesure = mesureService.ajouterMesure(mesure);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleMesure);
    }

    @GetMapping("/source/{sourceId}")
    @Operation(summary = "Obtenir les mesures par source")
    public ResponseEntity<List<MesureAnalyse>> obtenirMesuresParSource(@PathVariable String sourceId) {
        return ResponseEntity.ok(mesureService.obtenirMesuresParSource(sourceId));
    }

    @GetMapping("/indicateur/{indicateur}")
    @Operation(summary = "Obtenir les mesures par indicateur")
    public ResponseEntity<List<MesureAnalyse>> obtenirMesuresParIndicateur(@PathVariable String indicateur) {
        return ResponseEntity.ok(mesureService.obtenirMesuresParIndicateur(indicateur));
    }
}