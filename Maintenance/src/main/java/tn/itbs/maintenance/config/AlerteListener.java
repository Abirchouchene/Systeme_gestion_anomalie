package tn.itbs.maintenance.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.itbs.maintenance.dto.AlerteDTO;
import tn.itbs.maintenance.dto.AlerteEvent;
import tn.itbs.maintenance.entity.Intervention;
import tn.itbs.maintenance.repositories.InterventionRepository;
import tn.itbs.maintenance.service.InterventionService;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlerteListener {

    private final InterventionService interventionService;

    @RabbitListener(queues = "anomalie.queue")
    public void recevoirAlerte(AlerteDTO alerteDTO) {
        log.info("📥 Réception d'une nouvelle alerte via RabbitMQ: ID={}, Type={}, Gravité={}",
                alerteDTO.getId(), alerteDTO.getType(), alerteDTO.getNiveauGravite());

        try {
            // Création automatique d'une intervention
            interventionService.creerInterventionAutomatique(alerteDTO);
            log.info("✅ Intervention créée automatiquement pour l'alerte {}", alerteDTO.getId());
        } catch (Exception e) {
            log.error("❌ Erreur lors de la création de l'intervention pour l'alerte {}: {}",
                    alerteDTO.getId(), e.getMessage());
        }
    }
}