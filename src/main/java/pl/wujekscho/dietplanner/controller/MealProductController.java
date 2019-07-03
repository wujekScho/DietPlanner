package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.MealProduct;
import pl.wujekscho.dietplanner.repository.MealProductRepository;

@RestController
@RequestMapping("/meal_products")
public class MealProductController {
    private final MealProductRepository mealProductRepository;

    public MealProductController(MealProductRepository mealProductRepository) {
        this.mealProductRepository = mealProductRepository;
    }


    @GetMapping("/{id}")
    public MealProduct getById(@PathVariable Long id) {
        return mealProductRepository.getOne(id);
    }
}
