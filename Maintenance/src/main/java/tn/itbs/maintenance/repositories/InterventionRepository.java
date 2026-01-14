package tn.itbs.maintenance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.maintenance.entity.Intervention;

import java.util.List;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByAlerteId(Long alerteId);
    List<Intervention> findByTechnicienId(Long technicienId);
    List<Intervention> findByStatut(String statut);
}



