package pl.wujekscho.dietplanner.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "planned_days")
public class PlannedDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate day;
    @ManyToOne
    DayMeals dayMeals;
    @ManyToOne
    User user;

    public PlannedDay(LocalDate day, DayMeals dayMeals, User user) {
        this.day = day;
        this.dayMeals = dayMeals;
        this.user = user;
    }
}
