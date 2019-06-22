package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.entity.PlannedDay;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.model.PlannedDayId;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;
import pl.wujekscho.dietplanner.repository.PlannedDayRepository;
import pl.wujekscho.dietplanner.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlannedDayService {
    private PlannedDayRepository plannedDayRepository;
    private DayMealsRepository dayMealsRepository;
    private UserRepository userRepository;

    public PlannedDayService(PlannedDayRepository plannedDayRepository, DayMealsRepository dayMealsRepository, UserRepository userRepository) {
        this.plannedDayRepository = plannedDayRepository;
        this.dayMealsRepository = dayMealsRepository;
        this.userRepository = userRepository;
    }

    public List<PlannedDay> findAllByUserId(Long userId) {
        return plannedDayRepository.findAllByUserIdOrderByMealsDate(userId);
    }

    public PlannedDay save(PlannedDayId plannedDayId) {
        DayMeals dayMeals = dayMealsRepository.getOne(plannedDayId.getDayMealsId());
        User user = userRepository.getOne(plannedDayId.getUserId());
        LocalDate mealsDate = plannedDayId.getMealsDate();
        PlannedDay plannedDay = new PlannedDay(mealsDate, dayMeals, user);
        System.out.println(plannedDay.getMealsDate());
        return plannedDayRepository.save(plannedDay);
    }

}
