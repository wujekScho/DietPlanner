package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.service.DayMealsService;

import java.util.List;

@RestController
@RequestMapping("/day_meals")
public class DayMealsController {
    private DayMealsService dayMealsService;

    public DayMealsController(DayMealsService dayMealsService) {
        this.dayMealsService = dayMealsService;
    }

    @GetMapping("")
    public List<DayMeals> getAll() {
        return dayMealsService.findAllDayMeals();
    }

    @GetMapping("/user/{id}")
    public List<DayMeals> getAllByUserId(@PathVariable Long id) {
        return dayMealsService.findAllByUserId(id);
    }

    @GetMapping("/{id}")
    public DayMeals getById(@PathVariable Long id) {
        return dayMealsService.getById(id);
    }
}
