package tn.itbs.surveillance.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.itbs.surveillance.entity.Alerte;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlerteDTO {
    private Long id;
    private String type;
    private String message;
    private String niveauGravite;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    private LocalDateTime dateDetection;
    public static AlerteDTO fromEntity(Alerte alerte) {
        return new AlerteDTO(
                alerte.getId(),
                alerte.getType(),
                alerte.getMessage(),
                alerte.getNiveauGravite(),
                alerte.getDateDetection()
        );
    }
}

