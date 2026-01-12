package tn.itbs.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AlerteEvent {

    private Long id;
    private String type;
    private String niveauGravite;
    private String message;
    private LocalDateTime dateDetection;

    // getters & setters
}

