package pl.wujekscho.dietplanner.dbtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wujekscho.dietplanner.entity.*;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;
import pl.wujekscho.dietplanner.repository.MealProductRepository;
import pl.wujekscho.dietplanner.repository.MealRepository;
import pl.wujekscho.dietplanner.repository.ProductRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@Component
public class DBFeeder {
    private final ProductRepository productRepository;
    private final MealProductRepository mealProductRepository;
    private final MealRepository mealRepository;
    private final DayMealsRepository dayMealsRepository;

    @Autowired
    public DBFeeder(ProductRepository productRepository, MealProductRepository mealProductRepository,
                    MealRepository mealRepository, DayMealsRepository dayMealsRepository) {
        this.productRepository = productRepository;
        this.mealProductRepository = mealProductRepository;
        this.mealRepository = mealRepository;
        this.dayMealsRepository = dayMealsRepository;
    }


    public void importProducts() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("imports/products.txt").toURI());

            Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                    .map(s -> s.split(";"))
                    .forEach(s -> {
                        Product product = new Product();
                        product.setName(s[0].replaceAll("^[^a-zA-ZĘÓĄŚŁŻŹĆŃęóąśłżźćń]", ""));
                        Optional<ProductType> productType = Arrays.stream(ProductType.values())
                                .filter(p -> p.getType().equalsIgnoreCase(s[1].trim()))
                                .findFirst();
                        product.setProductType(productType.orElse(ProductType.INNE));
                        product.setCalories(Double.valueOf(s[2].replace(",", ".")).intValue());
                        product.setProtein(Double.valueOf(s[3].replace(",", ".")));
                        product.setFat(Double.valueOf(s[4].replace(",", ".")));
                        product.setCarbohydrates(Double.valueOf(s[5].replace(",", ".")));
                        if (s.length == 9) {
                            product.setHomeMeasureType(s[6]);
                            product.setHomeMeasureWeightRatio(Double.valueOf(s[7].replace(",", ".")));
                            product.setHomeMeasureStep(Double.valueOf(s[8].replace(",", ".")));
                        }
                        productRepository.save(product);
                    });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importDays() {
        try {
            String mealType = "";
            DayMeals dayMeals = null;
            Meal meal = null;
            Path path = Paths.get(ClassLoader.getSystemResource("imports/days.txt").toURI());
            int i = 1;
            for (String line : Files.readAllLines(path)) {
                String[] fields = line.split(";", -1);
                String type = fields[0].replaceAll("^[^a-zA-ZĘÓĄŚŁŻŹĆŃęóąśłżźćń]", "");
                String mealName = fields[1].trim();
                String recipe = fields[2].trim();
                String productName = fields[3].trim();
                String weight = fields[4].trim();
                System.out.println(String.format("%d|%s|%s|%s|%s|%s", i, type, mealName, recipe, productName, weight));
                i++;
                //Check if we process new meal
                if (!type.equalsIgnoreCase(mealType)) {
                    mealType = type;
                    //Check if we process new day, and initialize new day
                    if (type.equalsIgnoreCase("Śniadanie")) {
                        if (dayMeals != null) {
                            dayMealsRepository.save(dayMeals);
                        }
                        dayMeals = new DayMeals();
                    }

                    meal = new Meal();
                    Optional<MealType> mealType1 = Arrays.stream(pl.wujekscho.dietplanner.entity.MealType.values())
                            .filter(m -> m.getType().trim().equalsIgnoreCase(type))
                            .findFirst();
                    meal.setName(mealName);
                    meal.setMealType(mealType1.get());
                    if (!recipe.isEmpty()) {
                        meal.setRecipe(recipe);
                    }
                    //Initalization of new meal
                    if (meal != null) {
                        mealRepository.save(meal);
                    }
                    dayMeals.setMeal(meal);
                }
                //Every line new mealProduct is created and persisted
                MealProduct mealProduct = new MealProduct();
                mealProduct.setProduct(productRepository.findFirstByName(productName));
                if (!weight.isEmpty()) {
                    mealProduct.setWeight(Integer.valueOf(weight));
                }
                mealProductRepository.save(mealProduct);
                meal.getMealProducts().add(mealProduct);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
