package tn.itbs.maintenance.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tn.itbs.maintenance.dto.AlerteEvent;
import tn.itbs.maintenance.dto.*;
import java.util.List;

@FeignClient(name = "surveillance", path = "/api/v1/surveillance/alertes")
public interface SurveillanceClient {

    @GetMapping("/{id}")
    AlerteDTO obtenirAlerte(@PathVariable("id") Long id);

    @GetMapping
    List<AlerteDTO> obtenirToutesLesAlertes();

    @GetMapping("/gravite/{gravite}")
    List<AlerteDTO> obtenirAlertesParGravite(@PathVariable("gravite") String gravite);

    @GetMapping("/recentes")
    List<AlerteDTO> obtenirAlertesRecentes(@RequestParam(defaultValue = "24") int heures);
}

