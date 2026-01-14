package tn.itbs.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.surveillance.entity.MesureAnalyse;

import java.util.List;

public interface MesureAnalyseRepository extends JpaRepository<MesureAnalyse, Long> {
    List<MesureAnalyse> findBySourceId(String sourceId);
    List<MesureAnalyse> findByIndicateur(String indicateur);
}

