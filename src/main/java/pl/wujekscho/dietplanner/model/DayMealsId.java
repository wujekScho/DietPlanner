package pl.wujekscho.dietplanner.model;

import lombok.Data;

@Data
public class DayMealsId {
    private Long userId;
    private Long breakfastId;
    private Long brunchId;
    private Long dinnerId;
    private Long teaId;
    private Long supperId;
}
