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
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProductType productType;
    @Column(nullable = false)
    Integer calories;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double protein = 0.0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double fat = 0.0;
    @Column(columnDefinition = "DECIMAL(7,1)")
    Double carbohydrates = 0.0;
    String homeMeasureType;
    Double homeMeasureWeightRatio;
    Double homeMeasureStep;
    @ManyToOne
    User user;

    public Product(String name, ProductType productType, Integer calories, Double protein, Double fat, Double carbohydrates) {
        this.name = name;
        this.productType = productType;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    public Product(String name, ProductType productType, Integer calories, Double protein, Double fat, Double carbohydrates, String homeMeasureType, Double homeMeasureWeightRatio, Double homeMeasureStep) {
        this.name = name;
        this.productType = productType;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.homeMeasureType = homeMeasureType;
        this.homeMeasureWeightRatio = homeMeasureWeightRatio;
        this.homeMeasureStep = homeMeasureStep;
    }
}
