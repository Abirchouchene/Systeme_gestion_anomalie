package tn.itbs.maintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.maintenance.entity.Technicien;

import java.util.List;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    List<Technicien> findByDisponibilite(Boolean disponibilite);
    List<Technicien> findBySpecialite(String specialite);
}

