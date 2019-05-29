package pl.wujekscho.dietplanner.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    Integer weight = 0;
    Integer calories = 0;
    @Column(scale = 1)
    Double protein = 0.0;
    @Column(scale = 1)
    Double fat = 0.0;
    @Column(scale = 1)
    Double carbohydrates = 0.0;

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

    public void setMeal(Meal meal) {
        switch (meal.getMealType().getType()) {
            case "Śniadanie":
                this.setBreakfast(meal);
                break;
            case "Drugie śniadanie":
                this.setBrunch(meal);
                break;
            case "Obiad":
                this.setDinner(meal);
                break;
            case "Podwieczorek":
                this.setTea(meal);
                break;
            case "Kolacja":
                this.setSupper(meal);
                break;
        }
    }

    @PrePersist
    @PreUpdate
    public void calculateProperties() {
        this.weight = 0;
        this.calories = 0;
        this.protein = 0.0;
        this.fat = 0.0;
        this.carbohydrates = 0.0;
        for (Meal meal : getDayMeals()) {
            if (meal != null) {
                this.weight += meal.getWeight();
                this.calories += meal.getCalories();
                this.protein += meal.getProtein();
                this.fat += meal.getFat();
                this.carbohydrates += meal.getCarbohydrates();
            }
        }
    }
}
