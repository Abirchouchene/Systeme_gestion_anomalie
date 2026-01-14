package tn.itbs.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlerteDTO {
    private Long id;
    private String type;
    private String message;
    private String niveauGravite;
    private LocalDateTime dateDetection;
}

