package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.model.DayMealsId;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;
import pl.wujekscho.dietplanner.repository.MealRepository;
import pl.wujekscho.dietplanner.repository.UserRepository;

import java.util.List;

@Service
public class DayMealsService {
    private DayMealsRepository dayMealsRepository;
    private UserRepository userRepository;
    private MealRepository mealRepository;

    public DayMealsService(DayMealsRepository dayMealsRepository, UserRepository userRepository, MealRepository mealRepository) {
        this.dayMealsRepository = dayMealsRepository;
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
    }

    public List<DayMeals> findAllDayMeals() {
        return dayMealsRepository.findAll();
    }

    public List<DayMeals> findAllByUserId(Long id) {
        return dayMealsRepository.getAllByUserId(id);
    }

    public DayMeals getById(Long id) {
        return dayMealsRepository.getOne(id);
    }

    public void save(DayMealsId dayMealsId) {
        DayMeals dayMeals = new DayMeals();
        dayMeals.setUser(userRepository.getOne(dayMealsId.getUserId()));
        dayMeals.setBreakfast(mealRepository.getOne(dayMealsId.getBreakfastId()));
        dayMeals.setBrunch(mealRepository.getOne(dayMealsId.getBrunchId()));
        dayMeals.setDinner(mealRepository.getOne(dayMealsId.getDinnerId()));
        dayMeals.setTea(mealRepository.getOne(dayMealsId.getTeaId()));
        dayMeals.setSupper(mealRepository.getOne(dayMealsId.getSupperId()));
        dayMealsRepository.save(dayMeals);
    }
}
