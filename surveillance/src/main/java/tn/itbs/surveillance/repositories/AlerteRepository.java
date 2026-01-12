package tn.itbs.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.surveillance.entity.Alerte;

public interface AlerteRepository extends JpaRepository<Alerte, Long> {
}
