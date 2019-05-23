package pl.wujekscho.dietplanner.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "meal_products")
public class MealProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Integer weight;
    @ManyToOne(fetch = FetchType.EAGER)
    Product product;

    public MealProduct(Integer weight, Product product) {
        this.weight = weight;
        this.product = product;
    }
}
