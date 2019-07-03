package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.Meal;
import pl.wujekscho.dietplanner.service.MealService;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {
    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/{id}")
    public Meal getById(@PathVariable Long id) {
        return mealService.getById(id);
    }

    @GetMapping("/user/{id}")
    public List<Meal> getUsersMeals(@PathVariable Long id) {
        return mealService.getAllByUserId(id);
    }
}
