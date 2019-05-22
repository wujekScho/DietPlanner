package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wujekscho.dietplanner.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
