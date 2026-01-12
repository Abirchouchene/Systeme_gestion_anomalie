package tn.itbs.maintenance.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tn.itbs.maintenance.dto.AlerteEvent;

import java.util.List;

@FeignClient(name = "surveillance", url = "http://localhost:8081")
public interface SurveillanceClient {

    @GetMapping("/alertes")
    List<AlerteEvent> getAlertes();
}

