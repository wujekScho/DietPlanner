package pl.wujekscho.dietplanner.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeightMeasurement {
    private Long userId;
    private LocalDate measurementDate;
    private Double weight;
}
