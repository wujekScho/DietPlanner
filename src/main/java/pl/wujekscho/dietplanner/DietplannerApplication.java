package pl.wujekscho.dietplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.wujekscho.dietplanner.entity.MealProduct;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.entity.ProductType;
import pl.wujekscho.dietplanner.repository.MealProductRepository;
import pl.wujekscho.dietplanner.repository.ProductRepository;

@SpringBootApplication
public class DietplannerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DietplannerApplication.class, args);
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        MealProductRepository mealProductRepository = ctx.getBean(MealProductRepository.class);
        Product mleko = new Product("Mleko", ProductType.NABIAŁ, 100, 20.0, 5.0, 4.5, null, null, null);
        productRepository.save(mleko);
        MealProduct mealProduct = new MealProduct(100, mleko);
        mealProductRepository.save(mealProduct);

    }
}
