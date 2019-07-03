package pl.wujekscho.dietplanner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.model.DayMealsId;
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

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody DayMealsId dayMealsId) {
        dayMealsService.save(dayMealsId);
    }
}
