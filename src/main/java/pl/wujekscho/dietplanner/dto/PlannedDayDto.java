package pl.wujekscho.dietplanner.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlannedDayDto {
    private LocalDate mealsDate;
    private Long dayMealsId;
    private Long userId;
}
