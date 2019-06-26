package pl.wujekscho.dietplanner.model;

import lombok.Data;

@Data
public class ShoppingListProduct {
    private String name;
    private Integer weight;
    private String homeMeasure;
    private String type;
}
