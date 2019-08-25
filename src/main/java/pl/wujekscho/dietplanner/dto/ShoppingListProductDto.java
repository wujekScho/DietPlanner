package pl.wujekscho.dietplanner.dto;

import lombok.Data;

@Data
public class ShoppingListProductDto {
    private String name;
    private Integer weight;
    private String homeMeasure;
    private String type;
}
