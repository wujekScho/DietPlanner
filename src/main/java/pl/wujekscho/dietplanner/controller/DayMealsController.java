package pl.wujekscho.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;

import java.util.List;

@RestController
@RequestMapping("/day_meals")
public class DayMealsController  {
    private final DayMealsRepository dayMealsRepository;

    public DayMealsController(DayMealsRepository dayMealsRepository) {
        this.dayMealsRepository = dayMealsRepository;
    }

    @GetMapping("")
    public List<DayMeals> getAll() {
        return dayMealsRepository.findAll();
    }

    @GetMapping("/{id}")
    public DayMeals getById(@PathVariable Long id) {
        return dayMealsRepository.getOne(id);
    }
}
