package pl.wujekscho.dietplanner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.entity.PlannedDay;
import pl.wujekscho.dietplanner.model.PlannedDayId;
import pl.wujekscho.dietplanner.service.PlannedDayService;

import java.util.List;

@RestController
@RequestMapping("/planned_days")
public class PlannedDayController {
    private PlannedDayService plannedDayService;

    public PlannedDayController(PlannedDayService plannedDayService) {
        this.plannedDayService = plannedDayService;
    }

    @GetMapping("/user/{id}")
    public List<PlannedDay> getAllByUserId(@PathVariable Long id) {
        return plannedDayService.findAllByUserId(id);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void savePlannedDay(@RequestBody PlannedDayId plannedDay) {
        System.out.println(plannedDay);
        plannedDayService.save(plannedDay);
    }

}
