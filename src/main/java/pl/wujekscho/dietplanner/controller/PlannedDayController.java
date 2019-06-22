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

    @GetMapping("/{id}")
    public PlannedDay getOneById(@PathVariable Long id) {
        return plannedDayService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<PlannedDay> getAllByUserId(@PathVariable Long id) {
        return plannedDayService.findAllByUserId(id);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody PlannedDayId plannedDay) {
        plannedDayService.save(plannedDay);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        plannedDayService.delete(id);
    }
}
