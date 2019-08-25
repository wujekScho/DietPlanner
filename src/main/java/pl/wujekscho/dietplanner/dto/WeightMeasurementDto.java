package pl.wujekscho.dietplanner.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class WeightMeasurementDto {
    private Long userId;
    private LocalDate measurementDate;
    private Double weight;
}
