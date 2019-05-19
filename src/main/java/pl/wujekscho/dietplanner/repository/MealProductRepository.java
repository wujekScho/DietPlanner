package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wujekscho.dietplanner.entity.MealProduct;

@Repository
public interface MealProductRepository extends JpaRepository<MealProduct, Long> {
}
