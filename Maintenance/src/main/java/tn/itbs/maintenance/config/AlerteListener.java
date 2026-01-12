package tn.itbs.maintenance.config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.itbs.maintenance.dto.AlerteEvent;
import tn.itbs.maintenance.entity.Intervention;
import tn.itbs.maintenance.repositories.InterventionRepository;

import java.time.LocalDate;

@Component
public class AlerteListener {

    @Autowired
    private InterventionRepository interventionRepo;

    @RabbitListener(queues = "alerte.queue")
    public void recevoirAlerte(AlerteEvent alerte) {

        Intervention intervention = new Intervention();
        intervention.setAlerteId(alerte.getId());
        intervention.setDatePlanifiee(LocalDate.now().plusDays(1));
        intervention.setStatut("PLANIFIEE");

        interventionRepo.save(intervention);
    }
}

