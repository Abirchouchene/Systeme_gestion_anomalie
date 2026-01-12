package tn.itbs.surveillance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alerte {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String message;
    private String niveauGravite;
    private LocalDateTime dateDetection;
}

