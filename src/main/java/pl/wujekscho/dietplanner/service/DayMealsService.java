package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.dto.DayMealsDto;
import pl.wujekscho.dietplanner.entity.DayMeals;
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
        List<DayMeals> dayMealsList = dayMealsRepository.getAllByUserId(id);
        dayMealsList.forEach(dayMeals -> {
            calibrateToNeeds(dayMeals, id);
        });
        return dayMealsList;
    }

    public DayMeals getById(Long id) {
        return dayMealsRepository.getOne(id);
    }

    public void save(DayMealsDto dayMealsDto) {
        DayMeals dayMeals = new DayMeals();
        dayMeals.setUser(userRepository.getOne(dayMealsDto.getUserId()));
        dayMeals.setBreakfast(mealRepository.getOne(dayMealsDto.getBreakfastId()));
        dayMeals.setBrunch(mealRepository.getOne(dayMealsDto.getBrunchId()));
        dayMeals.setDinner(mealRepository.getOne(dayMealsDto.getDinnerId()));
        dayMeals.setTea(mealRepository.getOne(dayMealsDto.getTeaId()));
        dayMeals.setSupper(mealRepository.getOne(dayMealsDto.getSupperId()));
        dayMealsRepository.save(dayMeals);
    }

    public void calibrateToNeeds(DayMeals dayMeals, Long userId) {
        int caloriesNeeded = userRepository.getOne(userId).getCaloriesNeeded();
        int dayMealsCalories = dayMeals.getCalories();
        double ratio = (double) caloriesNeeded / dayMealsCalories;
        dayMeals.getDayMeals().forEach(meal -> {
            meal.getMealProducts().forEach(mealProduct -> {
                mealProduct.setWeight(Math.round((int) (mealProduct.getWeight() * ratio)));
                mealProduct.calculateProperties();
            });
            meal.calculateProperties();
        });
        dayMeals.calculateProperties();
    }
}
