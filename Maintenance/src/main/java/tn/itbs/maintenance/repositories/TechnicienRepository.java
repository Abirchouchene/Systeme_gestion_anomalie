package tn.itbs.maintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.maintenance.entity.Technicien;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
}

