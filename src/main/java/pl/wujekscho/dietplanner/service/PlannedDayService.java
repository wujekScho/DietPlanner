package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.dto.PlannedDayDto;
import pl.wujekscho.dietplanner.dto.ShoppingListProductDto;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.entity.PlannedDay;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;
import pl.wujekscho.dietplanner.repository.PlannedDayRepository;
import pl.wujekscho.dietplanner.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlannedDayService {
    private PlannedDayRepository plannedDayRepository;
    private DayMealsRepository dayMealsRepository;
    private UserRepository userRepository;
    private DayMealsService dayMealsService;

    public PlannedDayService(PlannedDayRepository plannedDayRepository, DayMealsRepository dayMealsRepository, UserRepository userRepository, DayMealsService dayMealsService) {
        this.plannedDayRepository = plannedDayRepository;
        this.dayMealsRepository = dayMealsRepository;
        this.userRepository = userRepository;
        this.dayMealsService = dayMealsService;
    }

    public List<PlannedDay> findAllByUserId(Long userId) {
        List<PlannedDay> usersPlannedDays = plannedDayRepository.findAllByUserIdOrderByMealsDate(userId);
        usersPlannedDays.forEach(plannedDay -> {
            dayMealsService.calibrateToNeeds(plannedDay.getDayMeals(), userId);
        });
        return usersPlannedDays;
    }

    public PlannedDay findById(Long id) {
        PlannedDay plannedDay = plannedDayRepository.getOne(id);
        Long userId = plannedDay.getUser().getId();
        dayMealsService.calibrateToNeeds(plannedDay.getDayMeals(), userId);
        return plannedDay;
    }

    public PlannedDay save(PlannedDayDto plannedDayDto) {
        DayMeals dayMeals = dayMealsRepository.getOne(plannedDayDto.getDayMealsId());
        User user = userRepository.getOne(plannedDayDto.getUserId());
        LocalDate mealsDate = plannedDayDto.getMealsDate();
        PlannedDay plannedDay = new PlannedDay(mealsDate, dayMeals, user);
        System.out.println(plannedDay.getMealsDate());
        return plannedDayRepository.save(plannedDay);
    }

    public void delete(Long id) {
        plannedDayRepository.deleteById(id);

    }

    public List<ShoppingListProductDto> getShoppingList(List<Long> plannedDaysIds) {
        List<ShoppingListProductDto> shoppingList = new ArrayList<>();
        plannedDaysIds.forEach(id -> {
            PlannedDay plannedDay = plannedDayRepository.getOne(id);
            dayMealsService.calibrateToNeeds(plannedDay.getDayMeals(), plannedDay.getUser().getId());
            plannedDay.getDayMeals().getDayMeals().forEach(meal -> {
                meal.getMealProducts().forEach(mealProduct -> {
                    Product product = mealProduct.getProduct();
                    Integer weight = mealProduct.getWeight();
                    ShoppingListProductDto shoppingListProductDto = new ShoppingListProductDto();
                    shoppingListProductDto.setWeight(weight);
                    shoppingListProductDto.setName(product.getName());
                    shoppingListProductDto.setType(product.getProductType().getType());
                    if (product.getHomeMeasureType() != null) {
                        String homeMeasure = String.format("%d x %s", Math.round(weight / product.getHomeMeasureWeightRatio()), product.getHomeMeasureType());
                        shoppingListProductDto.setHomeMeasure(homeMeasure);
                    }
                    shoppingList.add(shoppingListProductDto);
                });
            });
        });
        List<ShoppingListProductDto> groupedShoppingList = shoppingList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(ShoppingListProductDto::getName, Collectors.collectingAndThen(
                                Collectors.reducing((a, b) -> {
                                    ShoppingListProductDto shoppingListProductDto = new ShoppingListProductDto();
                                    shoppingListProductDto.setName(a.getName());
                                    shoppingListProductDto.setType(a.getType());
                                    shoppingListProductDto.setWeight(a.getWeight() + b.getWeight());
                                    if (a.getHomeMeasure() != null) {
                                        int aHomeMeasureValue = Integer.valueOf(a.getHomeMeasure().replaceAll("[^\\d]+", ""));
                                        int bHomeMeasureValue = Integer.valueOf(b.getHomeMeasure().replaceAll("[^\\d]+", ""));
                                        String newHomeMeasureValue = String.valueOf(aHomeMeasureValue + bHomeMeasureValue);
                                        shoppingListProductDto.setHomeMeasure(a.getHomeMeasure().replaceAll("\\d+", newHomeMeasureValue));
                                    }
                                    return shoppingListProductDto;
                                }), Optional::get)),
                        m -> new ArrayList<>(m.values())));
        List<ShoppingListProductDto> sortedShoppingList = groupedShoppingList.stream()
                .sorted(Comparator.comparing(ShoppingListProductDto::getType).thenComparing(ShoppingListProductDto::getName))
                .collect(Collectors.toList());
        return sortedShoppingList;
    }
}
