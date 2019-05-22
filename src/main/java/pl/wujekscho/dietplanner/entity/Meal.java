package pl.wujekscho.dietplanner.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    MealType mealType;
    String recipe;
    @ManyToMany
    List<MealProduct> mealProducts = new ArrayList<>();
    Integer mealWeight = 0;
    Integer mealCalories = 0;
    @Column(scale = 1)
    Double mealProtein = 0.0;
    @Column(scale = 1)
    Double mealFat = 0.0;
    @Column(scale = 1)
    Double mealCarbohydrates = 0.0;


    public Meal(MealType mealType) {
        this.mealType = mealType;
    }

    public Meal(String recipe, MealType mealType) {
        this.recipe = recipe;
        this.mealType = mealType;
    }

    public void calculateProperties() {
        this.mealWeight = 0;
        this.mealCalories = 0;
        this.mealProtein = 0.0;
        this.mealFat = 0.0;
        this.mealCarbohydrates = 0.0;
        for (MealProduct mealProduct : mealProducts) {
            this.mealWeight += mealProduct.getWeight();
            this.mealCalories += mealProduct.getProduct().getCalories();
            this.mealProtein += mealProduct.getProduct().getProtein();
            this.mealFat += mealProduct.getProduct().getFat();
            this.mealCarbohydrates += mealProduct.getProduct().getCarbohydrates();
        }
    }
}
