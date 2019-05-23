package pl.wujekscho.dietplanner.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product implements Serializable, EntityModel {
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
    @Column(nullable = false, scale = 1)
    Double protein;
    @Column(nullable = false, scale = 1)
    Double fat;
    @Column(nullable = false, scale = 1)
    Double carbohydrates;
    String homeMeasureType;
    Double homeMeasureWeightRadio;
    Double homeMeasureStep;

    public Product(String name, ProductType productType, Integer calories, Double protein, Double fat, Double carbohydrates) {
        this.name = name;
        this.productType = productType;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    public Product(String name, ProductType productType, Integer calories, Double protein, Double fat, Double carbohydrates, String homeMeasureType, Double homeMeasureWeightRadio, Double homeMeasureStep) {
        this.name = name;
        this.productType = productType;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.homeMeasureType = homeMeasureType;
        this.homeMeasureWeightRadio = homeMeasureWeightRadio;
        this.homeMeasureStep = homeMeasureStep;
    }
}
