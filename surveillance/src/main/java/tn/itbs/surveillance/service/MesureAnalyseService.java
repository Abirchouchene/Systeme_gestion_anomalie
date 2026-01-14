package tn.itbs.surveillance.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.itbs.surveillance.entity.Alerte;
import tn.itbs.surveillance.entity.MesureAnalyse;
import tn.itbs.surveillance.repositories.MesureAnalyseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MesureAnalyseService {

    private final MesureAnalyseRepository mesureRepository;
    private final AlerteService alerteService;

    // Seuils de détection d'anomalies
    private static final double SEUIL_CRITIQUE = 90.0;
    private static final double SEUIL_ELEVE = 75.0;
    private static final double SEUIL_MOYEN = 50.0;

    @Transactional
    public MesureAnalyse ajouterMesure(MesureAnalyse mesure) {
        mesure.setDate(LocalDateTime.now());
        MesureAnalyse mesureSauvegardee = mesureRepository.save(mesure);

        log.info("📊 Nouvelle mesure enregistrée: Source={}, Indicateur={}, Valeur={}",
                mesure.getSourceId(), mesure.getIndicateur(), mesure.getValeur());

        // Détection automatique d'anomalies
        detecterAnomalie(mesureSauvegardee);

        return mesureSauvegardee;
    }

    private void detecterAnomalie(MesureAnalyse mesure) {
        String niveauGravite = null;
        String message = null;

        if (mesure.getValeur() >= SEUIL_CRITIQUE) {
            niveauGravite = "CRITIQUE";
            message = String.format("⚠️ ANOMALIE CRITIQUE détectée! Source: %s, Indicateur: %s, Valeur: %.2f (seuil: %.2f)",
                    mesure.getSourceId(), mesure.getIndicateur(), mesure.getValeur(), SEUIL_CRITIQUE);
        } else if (mesure.getValeur() >= SEUIL_ELEVE) {
            niveauGravite = "ELEVE";
            message = String.format("⚠️ Anomalie élevée détectée. Source: %s, Indicateur: %s, Valeur: %.2f (seuil: %.2f)",
                    mesure.getSourceId(), mesure.getIndicateur(), mesure.getValeur(), SEUIL_ELEVE);
        } else if (mesure.getValeur() >= SEUIL_MOYEN) {
            niveauGravite = "MOYEN";
            message = String.format("⚡ Anomalie moyenne détectée. Source: %s, Indicateur: %s, Valeur: %.2f (seuil: %.2f)",
                    mesure.getSourceId(), mesure.getIndicateur(), mesure.getValeur(), SEUIL_MOYEN);
        }

        if (niveauGravite != null) {
            creerAlerte(mesure, niveauGravite, message);
        }
    }

    private void creerAlerte(MesureAnalyse mesure, String gravite, String message) {
        Alerte alerte = new Alerte();
        alerte.setType("ANOMALIE_" + mesure.getIndicateur().toUpperCase());
        alerte.setMessage(message);
        alerte.setNiveauGravite(gravite);

        alerteService.creerAlerte(alerte);
    }

    public List<MesureAnalyse> obtenirMesuresParSource(String sourceId) {
        return mesureRepository.findBySourceId(sourceId);
    }

    public List<MesureAnalyse> obtenirMesuresParIndicateur(String indicateur) {
        return mesureRepository.findByIndicateur(indicateur);
    }
}