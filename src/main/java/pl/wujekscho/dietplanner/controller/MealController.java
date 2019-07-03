package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.Meal;
import pl.wujekscho.dietplanner.repository.MealRepository;

@RestController
@RequestMapping("/meals")
public class MealController {
    private final MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @GetMapping("/{id}")
    public Meal getById(@PathVariable Long id) {
        return mealRepository.getOne(id);
    }
}
