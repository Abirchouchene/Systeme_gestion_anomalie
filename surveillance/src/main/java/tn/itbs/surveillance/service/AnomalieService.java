package tn.itbs.surveillance.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.surveillance.config.RabbitConfig;
import tn.itbs.surveillance.entity.Alerte;
import tn.itbs.surveillance.entity.MesureAnalyse;
import tn.itbs.surveillance.repositories.AlerteRepository;
import tn.itbs.surveillance.repositories.MesureAnalyseRepository;

import java.time.LocalDateTime;

@Service
public class AnomalieService {

    @Autowired
    private MesureAnalyseRepository mesureRepo;

    @Autowired
    private AlerteRepository alerteRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public MesureAnalyse analyser(MesureAnalyse mesure) {

        mesureRepo.save(mesure);

        if (mesure.getValeur() > 80) {

            Alerte alerte = new Alerte();
            alerte.setType("TEMPERATURE");
            alerte.setMessage("Valeur anormale détectée");
            alerte.setNiveauGravite("CRITIQUE");
            alerte.setDateDetection(LocalDateTime.now());

            alerteRepo.save(alerte);

            // Publication RabbitMQ
            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    RabbitConfig.ROUTING_KEY,
                    alerte
            );
        }

        return mesure;
    }
}
