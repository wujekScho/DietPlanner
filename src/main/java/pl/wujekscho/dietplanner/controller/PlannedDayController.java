package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.PlannedDay;
import pl.wujekscho.dietplanner.repository.PlannedDayRepository;

import java.util.List;

@RestController
@RequestMapping("/planned_days")
public class PlannedDayController {
    private PlannedDayRepository plannedDayRepository;


    public PlannedDayController(PlannedDayRepository plannedDayRepository) {
        this.plannedDayRepository = plannedDayRepository;
    }

    @GetMapping("/user/{id}")
    public List<PlannedDay> getAllByUserId (@PathVariable Long id) {
        return  plannedDayRepository.findAllByUserIdOrderByDay(id);
    }

}
