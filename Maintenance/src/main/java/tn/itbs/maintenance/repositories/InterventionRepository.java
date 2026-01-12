package tn.itbs.maintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.maintenance.entity.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
}

