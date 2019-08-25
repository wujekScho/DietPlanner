package pl.wujekscho.dietplanner.dto;

import lombok.Data;

@Data
public class DayMealsDto {
    private Long userId;
    private Long breakfastId;
    private Long brunchId;
    private Long dinnerId;
    private Long teaId;
    private Long supperId;
}
