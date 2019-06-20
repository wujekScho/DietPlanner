package pl.wujekscho.dietplanner.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "meal_products")
public class MealProduct implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer weight;
    @ManyToOne(fetch = FetchType.EAGER)
    Product product;
    Integer calories = 0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double protein = 0.0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double fat = 0.0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double carbohydrates = 0.0;


    public MealProduct(Integer weight, Product product) {
        this.weight = weight;
        this.product = product;
    }

    @PrePersist
    @PreUpdate
    public void calculateProperties() {
        if (weight != null) {
            if (this.product.getHomeMeasureType() != null) {
                Double step = this.product.getHomeMeasureWeightRatio() * this.product.getHomeMeasureStep();
                this.weight = (int) (Math.round(this.weight / (step)) * step);
            }
            Double ratio = (double) this.weight / 100;
            this.calories = (int) (ratio * this.product.getCalories());
            this.protein = ratio * this.product.getProtein();
            this.fat = ratio * this.product.getFat();
            this.carbohydrates = ratio * this.product.getCarbohydrates();
        }
    }
}
