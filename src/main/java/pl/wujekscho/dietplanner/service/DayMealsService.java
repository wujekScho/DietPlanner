package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;

import java.util.List;

@Service
public class DayMealsService {
    private DayMealsRepository dayMealsRepository;

    public DayMealsService(DayMealsRepository dayMealsRepository) {
        this.dayMealsRepository = dayMealsRepository;
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
}
