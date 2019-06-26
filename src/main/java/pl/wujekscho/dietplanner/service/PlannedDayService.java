package pl.wujekscho.dietplanner.service;

import org.springframework.stereotype.Service;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.entity.PlannedDay;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.model.PlannedDayId;
import pl.wujekscho.dietplanner.model.ShoppingListProduct;
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

    public PlannedDayService(PlannedDayRepository plannedDayRepository, DayMealsRepository dayMealsRepository, UserRepository userRepository) {
        this.plannedDayRepository = plannedDayRepository;
        this.dayMealsRepository = dayMealsRepository;
        this.userRepository = userRepository;
    }

    public List<PlannedDay> findAllByUserId(Long userId) {
        return plannedDayRepository.findAllByUserIdOrderByMealsDate(userId);
    }

    public PlannedDay findById(Long id) {
        return plannedDayRepository.getOne(id);
    }

    public PlannedDay save(PlannedDayId plannedDayId) {
        DayMeals dayMeals = dayMealsRepository.getOne(plannedDayId.getDayMealsId());
        User user = userRepository.getOne(plannedDayId.getUserId());
        LocalDate mealsDate = plannedDayId.getMealsDate();
        PlannedDay plannedDay = new PlannedDay(mealsDate, dayMeals, user);
        System.out.println(plannedDay.getMealsDate());
        return plannedDayRepository.save(plannedDay);
    }

    public void delete(Long id) {
        plannedDayRepository.deleteById(id);

    }

    public List<ShoppingListProduct> getShoppingList(List<Long> plannedDaysIds) {
        List<ShoppingListProduct> shoppingList = new ArrayList<>();
        plannedDaysIds.forEach(id -> {
            PlannedDay plannedDay = plannedDayRepository.getOne(id);
            plannedDay.getDayMeals().getDayMeals().forEach(meal -> {
                meal.getMealProducts().forEach(mealProduct -> {
                    Product product = mealProduct.getProduct();
                    Integer weight = mealProduct.getWeight();
                    ShoppingListProduct shoppingListProduct = new ShoppingListProduct();
                    shoppingListProduct.setWeight(weight);
                    shoppingListProduct.setName(product.getName());
                    shoppingListProduct.setType(product.getProductType().getType());
                    if (product.getHomeMeasureType() != null) {
                        String homeMeasure = String.format("%d x %s", Math.round(weight / product.getHomeMeasureWeightRatio()), product.getHomeMeasureType());
                        shoppingListProduct.setHomeMeasure(homeMeasure);
                    }
                    shoppingList.add(shoppingListProduct);
                });
            });
        });
        List<ShoppingListProduct> groupedShoppingList = shoppingList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(ShoppingListProduct::getName, Collectors.collectingAndThen(
                                Collectors.reducing((a, b) -> {
                                    ShoppingListProduct shoppingListProduct = new ShoppingListProduct();
                                    shoppingListProduct.setName(a.getName());
                                    shoppingListProduct.setType(a.getType());
                                    shoppingListProduct.setWeight(a.getWeight() + b.getWeight());
                                    if (a.getHomeMeasure() != null) {
                                        int aHomeMeasureValue = Integer.valueOf(a.getHomeMeasure().replaceAll("[^\\d]+", ""));
                                        int bHomeMeasureValue = Integer.valueOf(b.getHomeMeasure().replaceAll("[^\\d]+", ""));
                                        String newHomeMeasureValue = String.valueOf(aHomeMeasureValue + bHomeMeasureValue);
                                        shoppingListProduct.setHomeMeasure(a.getHomeMeasure().replaceAll("\\d+", newHomeMeasureValue));
                                    }
                                    return shoppingListProduct;
                                }), Optional::get)),
                        m -> new ArrayList<>(m.values())));
        List<ShoppingListProduct> sortedShoppingList = groupedShoppingList.stream()
                .sorted(Comparator.comparing(ShoppingListProduct::getType).thenComparing(ShoppingListProduct::getName))
                .collect(Collectors.toList());

        return sortedShoppingList;
    }
}
