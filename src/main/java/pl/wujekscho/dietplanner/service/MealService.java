package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.entity.Meal;
import pl.wujekscho.dietplanner.repository.MealRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {
    private MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal getById(Long id) {
        return mealRepository.getOne(id);
    }

    public List<Meal> getAllByUserId(Long id) {
        List<Meal> meals = mealRepository.getAllByUserId(id);
        List<Meal> sorted = meals.stream().sorted(Comparator.comparing(Meal::getName)).collect(Collectors.toList());
        return sorted;
    }
}
