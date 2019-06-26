package pl.wujekscho.dietplanner.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlannedDayId {
    private LocalDate mealsDate;
    private Long dayMealsId;
    private Long userId;
}
