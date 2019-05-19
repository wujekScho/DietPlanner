package pl.wujekscho.dietplanner.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "meal_products")
public class MealProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer weight;
    @ManyToOne
    Product product;

    public MealProduct(Integer weight, Product product) {
        this.weight = weight;
        this.product = product;
    }
}
