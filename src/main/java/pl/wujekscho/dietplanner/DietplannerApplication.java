package pl.wujekscho.dietplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.wujekscho.dietplanner.dbtools.DBFeeder;
import pl.wujekscho.dietplanner.entity.DayMeals;
import pl.wujekscho.dietplanner.entity.Meal;
import pl.wujekscho.dietplanner.entity.MealProduct;
import pl.wujekscho.dietplanner.entity.MealType;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;
import pl.wujekscho.dietplanner.repository.MealProductRepository;
import pl.wujekscho.dietplanner.repository.MealRepository;
import pl.wujekscho.dietplanner.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class DietplannerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DietplannerApplication.class, args);
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        MealProductRepository mealProductRepository = ctx.getBean(MealProductRepository.class);
        MealRepository mealRepository = ctx.getBean(MealRepository.class);
        DayMealsRepository dayMealsRepository = ctx.getBean(DayMealsRepository.class);
        DBFeeder dbFeeder = ctx.getBean(DBFeeder.class);
        if (productRepository.count() == 0) {
            dbFeeder.importProdutcts();
        }
//        MealProduct awokado = new MealProduct(141, productRepository.getOne(1L));
//        MealProduct banan = new MealProduct(115, productRepository.getOne(2L));
//        mealProductRepository.save(awokado);
//        mealProductRepository.save(banan);
//        Meal salatkaAwokado = new Meal("Sałatka owocowa", "Wymieszaj banana z pokrojonym awokado.", MealType.DRUGIE_ŚNIADANIE);
//        List<MealProduct> mealProducts = salatkaAwokado.getMealProducts();
//        mealProducts.add(awokado);
//        mealProducts.add(banan);
//        mealRepository.save(salatkaAwokado);
//        DayMeals day1 = new DayMeals(salatkaAwokado, salatkaAwokado, salatkaAwokado, salatkaAwokado, salatkaAwokado);
//        dayMealsRepository.save(day1);
        System.out.println(productRepository.findFirstByName("Awokado"));
    }
}
