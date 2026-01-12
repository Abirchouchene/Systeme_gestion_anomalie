package tn.itbs.surveillance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.itbs.surveillance.entity.MesureAnalyse;
import tn.itbs.surveillance.service.AnomalieService;

@RestController
@RequestMapping("/mesures")
public class MesureController {

    @Autowired
    private AnomalieService anomalieService;

    @PostMapping
    public MesureAnalyse envoyerMesure(@RequestBody MesureAnalyse mesure) {
        return anomalieService.analyser(mesure);
    }
}

