package tn.itbs.maintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.itbs.maintenance.entity.Intervention;
import tn.itbs.maintenance.repositories.InterventionRepository;

import java.util.List;

@RestController
@RequestMapping("/interventions")
public class InterventionController {

    @Autowired
    private InterventionRepository interventionRepo;

    @GetMapping
    public List<Intervention> getAll() {
        return interventionRepo.findAll();
    }

    @PutMapping("/{id}")
    public Intervention updateStatut(
            @PathVariable Long id,
            @RequestParam String statut) {

        Intervention intervention = interventionRepo.findById(id).orElseThrow();
        intervention.setStatut(statut);
        return interventionRepo.save(intervention);
    }
}
