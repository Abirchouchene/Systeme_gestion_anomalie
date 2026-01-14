package tn.itbs.surveillance.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tn.itbs.surveillance.entity.Alerte;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertePublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publierNouvelleAlerte(Alerte alerte) {
        log.info("📤 Publication de l'alerte {} vers RabbitMQ - Type: {}, Gravité: {}",
                alerte.getId(), alerte.getType(), alerte.getNiveauGravite());

        try {
            rabbitTemplate.convertAndSend(
                    RabbitConfig.ANOMALIE_EXCHANGE,
                    RabbitConfig.ANOMALIE_ROUTING_KEY,
                    alerte
            );
            log.info("✅ Alerte {} publiée avec succès", alerte.getId());
        } catch (Exception e) {
            log.error("❌ Erreur lors de la publication de l'alerte {}: {}", alerte.getId(), e.getMessage());
        }
    }
}