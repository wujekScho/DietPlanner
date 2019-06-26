package pl.wujekscho.dietplanner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.entity.PlannedDay;
import pl.wujekscho.dietplanner.model.PlannedDayId;
import pl.wujekscho.dietplanner.model.ShoppingListProduct;
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

    @PostMapping(path = "/shopping-list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ShoppingListProduct> getShoppingList(@RequestBody List<Long> plannedDaysIds) {
        return plannedDayService.getShoppingList(plannedDaysIds);
    }
//
//    @GetMapping(path = "/shopping-list", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void getShoppingList(@RequestBody int[] plannedDaysIds) {
//        System.out.println(plannedDaysIds);
//    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody PlannedDayId plannedDay) {
        plannedDayService.save(plannedDay);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        plannedDayService.delete(id);
    }
}
