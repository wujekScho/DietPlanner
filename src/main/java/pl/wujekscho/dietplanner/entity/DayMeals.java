package pl.wujekscho.dietplanner.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "day_meals")
public class DayMeals implements Serializable, EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    Meal breakfast;
    @ManyToOne(fetch = FetchType.EAGER)
    Meal brunch;
    @ManyToOne(fetch = FetchType.EAGER)
    Meal dinner;
    @ManyToOne(fetch = FetchType.EAGER)
    Meal tea;
    @ManyToOne(fetch = FetchType.EAGER)
    Meal supper;
    Integer dayWeight = 0;
    Integer dayCalories = 0;
    @Column(scale = 1)
    Double dayProtein = 0.0;
    @Column(scale = 1)
    Double dayFat = 0.0;
    @Column(scale = 1)
    Double dayCarbohydrates = 0.0;

    public DayMeals(Meal breakfast, Meal brunch, Meal dinner, Meal tea, Meal supper) {
        this.breakfast = breakfast;
        this.brunch = brunch;
        this.dinner = dinner;
        this.tea = tea;
        this.supper = supper;
    }

    public List<Meal> getDayMeals() {
        return Arrays.asList(this.breakfast, this.brunch, this.dinner, this.tea, this.supper);
    }

    @PrePersist
    @PreUpdate
    public void calculateProperties() {
        this.dayWeight = 0;
        this.dayCalories = 0;
        this.dayProtein = 0.0;
        this.dayFat = 0.0;
        this.dayCarbohydrates = 0.0;
        for (Meal meal : getDayMeals()) {
            if (meal != null) {
                this.dayWeight += meal.getMealWeight();
                this.dayCalories += meal.getMealCalories();
                this.dayProtein += meal.getMealProtein();
                this.dayFat += meal.getMealFat();
                this.dayCarbohydrates += meal.getMealCarbohydrates();
            }
        }
    }
}
