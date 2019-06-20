package pl.wujekscho.dietplanner.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "meals")
public class Meal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    MealType mealType;
    @Lob
    @Column(length = 1000)
    String recipe;
    @OneToMany
    List<MealProduct> mealProducts = new ArrayList<>();
    Integer weight = 0;
    Integer calories = 0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double protein = 0.0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double fat = 0.0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double carbohydrates = 0.0;


    public Meal(String name, MealType mealType) {
        this.name = name;
        this.mealType = mealType;
    }

    public Meal(String name, String recipe, MealType mealType) {
        this.name = name;
        this.recipe = recipe;
        this.mealType = mealType;
    }

    @PrePersist
    @PreUpdate
    public void calculateProperties() {
        this.weight = 0;
        this.calories = 0;
        this.protein = 0.0;
        this.fat = 0.0;
        this.carbohydrates = 0.0;
        for (MealProduct mealProduct : mealProducts) {
            this.weight += mealProduct.getWeight();
            this.calories += mealProduct.getCalories();
            this.protein += mealProduct.getProtein();
            this.fat += mealProduct.getFat();
            this.carbohydrates += mealProduct.getCarbohydrates();
        }
    }
}
