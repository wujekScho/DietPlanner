package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wujekscho.dietplanner.entity.MealProduct;

public interface MealProductRepository extends JpaRepository<MealProduct, Long> {
}
