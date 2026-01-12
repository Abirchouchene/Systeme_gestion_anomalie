package tn.itbs.surveillance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.itbs.surveillance.entity.Alerte;
import tn.itbs.surveillance.repositories.AlerteRepository;

import java.util.List;

@RestController
@RequestMapping("/alertes")
public class AlerteController {

    @Autowired
    private AlerteRepository alerteRepo;

    @GetMapping
    public List<Alerte> getAllAlertes() {
        return alerteRepo.findAll();
    }
}

