package pl.wujekscho.dietplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.wujekscho.dietplanner.dbtools.DBFeeder;
import pl.wujekscho.dietplanner.repository.DayMealsRepository;
import pl.wujekscho.dietplanner.repository.ProductRepository;

@SpringBootApplication
public class DietplannerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DietplannerApplication.class, args);
        importData(ctx);
    }

    public static void importData(ConfigurableApplicationContext ctx) {
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        DayMealsRepository dayMealsRepository = ctx.getBean(DayMealsRepository.class);
        DBFeeder dbFeeder = ctx.getBean(DBFeeder.class);
        if (productRepository.count() == 0) {
            dbFeeder.importProducts();
        }
        if (dayMealsRepository.count() == 0) {
            dbFeeder.importDays();
        }
    }
}
