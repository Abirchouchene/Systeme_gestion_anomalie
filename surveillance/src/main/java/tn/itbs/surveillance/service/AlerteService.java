package tn.itbs.surveillance.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.itbs.surveillance.config.AlertePublisher;
import tn.itbs.surveillance.entity.Alerte;
import tn.itbs.surveillance.repositories.AlerteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlerteService {

    private final AlerteRepository alerteRepository;
    private final AlertePublisher alertePublisher;

    @Transactional
    public Alerte creerAlerte(Alerte alerte) {
        // Définir la date de détection
        alerte.setDateDetection(LocalDateTime.now());

        // Sauvegarder l'alerte
        Alerte alerteSauvegardee = alerteRepository.save(alerte);
        log.info("🔔 Nouvelle alerte créée: ID={}, Type={}, Gravité={}",
                alerteSauvegardee.getId(), alerteSauvegardee.getType(), alerteSauvegardee.getNiveauGravite());

        // Publication asynchrone vers Maintenance via RabbitMQ
        alertePublisher.publierNouvelleAlerte(alerteSauvegardee);

        return alerteSauvegardee;
    }

    public List<Alerte> obtenirToutesLesAlertes() {
        return alerteRepository.findAll();
    }

    public Alerte obtenirAlerteParId(Long id) {
        return alerteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerte non trouvée avec l'ID: " + id));
    }

    public List<Alerte> obtenirAlertesParGravite(String gravite) {
        return alerteRepository.findByNiveauGravite(gravite);
    }

    public List<Alerte> obtenirAlertesRecentes(int heures) {
        LocalDateTime dateDebut = LocalDateTime.now().minusHours(heures);
        return alerteRepository.findByDateDetectionAfter(dateDebut);
    }
}