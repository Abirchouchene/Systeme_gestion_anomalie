package tn.itbs.maintenance.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.itbs.maintenance.client.SurveillanceClient;
import tn.itbs.maintenance.dto.AlerteDTO;
import tn.itbs.maintenance.entity.Intervention;
import tn.itbs.maintenance.entity.Technicien;
import tn.itbs.maintenance.repositories.InterventionRepository;
import tn.itbs.maintenance.repositories.TechnicienRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class InterventionService {

    private final InterventionRepository interventionRepository;
    private final TechnicienRepository technicienRepository;
    private final SurveillanceClient surveillanceClient;

    /**
     * Création automatique d'intervention suite à une alerte (Communication ASYNCHRONE via RabbitMQ)
     */
    @Transactional
    public Intervention creerInterventionAutomatique(AlerteDTO alerte) {
        log.info("🔧 Création automatique d'intervention pour l'alerte {}", alerte.getId());

        // Recherche d'un technicien disponible
        Technicien technicien = attribuerTechnicien(alerte);

        // Création de l'intervention
        Intervention intervention = new Intervention();
        intervention.setAlerteId(alerte.getId());
        intervention.setTechnicienId(technicien.getId());
        intervention.setDatePlanifiee(calculerDateIntervention(alerte.getNiveauGravite()));
        intervention.setStatut("EN_ATTENTE");

        Intervention interventionSauvegardee = interventionRepository.save(intervention);

        // Marquer le technicien comme indisponible
        technicien.setDisponibilite(false);
        technicienRepository.save(technicien);

        log.info("✅ Intervention {} créée - Technicien: {} - Date prévue: {}",
                interventionSauvegardee.getId(), technicien.getNom(), interventionSauvegardee.getDatePlanifiee());

        return interventionSauvegardee;
    }

    /**
     * Récupération des détails d'une alerte (Communication SYNCHRONE via Feign)
     */
    public AlerteDTO recupererDetailsAlerte(Long alerteId) {
        log.info("🔍 Récupération synchrone des détails de l'alerte {} depuis Surveillance", alerteId);
        try {
            AlerteDTO alerte = surveillanceClient.obtenirAlerte(alerteId);
            log.info("✅ Détails de l'alerte {} récupérés avec succès", alerteId);
            return alerte;
        } catch (Exception e) {
            log.error("❌ Erreur lors de la récupération de l'alerte {}: {}", alerteId, e.getMessage());
            throw new RuntimeException("Impossible de récupérer les détails de l'alerte", e);
        }
    }

    /**
     * Récupération de toutes les alertes depuis Surveillance
     */
    public List<AlerteDTO> recupererToutesLesAlertes() {
        log.info("🔍 Récupération synchrone de toutes les alertes depuis Surveillance");
        return surveillanceClient.obtenirToutesLesAlertes();
    }

    /**
     * Attribution d'un technicien selon la gravité et la spécialité
     */
    private Technicien attribuerTechnicien(AlerteDTO alerte) {
        List<Technicien> techniciensDisponibles = technicienRepository.findByDisponibilite(true);

        if (techniciensDisponibles.isEmpty()) {
            throw new RuntimeException("❌ Aucun technicien disponible pour l'intervention");
        }

        // Logique d'attribution basée sur la spécialité (à améliorer selon vos besoins)
        Technicien technicien = techniciensDisponibles.stream()
                .filter(t -> t.getSpecialite() != null &&
                        alerte.getType().contains(t.getSpecialite().toUpperCase()))
                .findFirst()
                .orElse(techniciensDisponibles.get(0));

        log.info("👷 Technicien attribué: {} (Spécialité: {})", technicien.getNom(), technicien.getSpecialite());
        return technicien;
    }

    /**
     * Calcul de la date d'intervention selon la gravité
     */
    private LocalDate calculerDateIntervention(String gravite) {
        LocalDate aujourdhui = LocalDate.now();

        return switch (gravite) {
            case "CRITIQUE" -> aujourdhui; // Intervention immédiate
            case "ELEVE" -> aujourdhui.plusDays(1); // Intervention sous 24h
            case "MOYEN" -> aujourdhui.plusDays(3); // Intervention sous 3 jours
            default -> aujourdhui.plusDays(7); // Intervention sous 1 semaine
        };
    }

    /**
     * Mise à jour du statut d'une intervention
     */
    @Transactional
    public Intervention mettreAJourStatut(Long id, String statut) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intervention non trouvée"));

        intervention.setStatut(statut);

        // Si l'intervention est terminée, libérer le technicien
        if ("TERMINEE".equals(statut)) {
            Technicien technicien = technicienRepository.findById(intervention.getTechnicienId())
                    .orElseThrow(() -> new RuntimeException("Technicien non trouvé"));
            technicien.setDisponibilite(true);
            technicienRepository.save(technicien);
            log.info("✅ Intervention {} terminée - Technicien {} à nouveau disponible", id, technicien.getNom());
        }

        return interventionRepository.save(intervention);
    }

    public List<Intervention> suivreInterventions() {
        return interventionRepository.findAll();
    }

    public List<Intervention> obtenirInterventionsParStatut(String statut) {
        return interventionRepository.findByStatut(statut);
    }
}