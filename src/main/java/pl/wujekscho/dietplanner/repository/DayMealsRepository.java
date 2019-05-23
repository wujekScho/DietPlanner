package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wujekscho.dietplanner.entity.DayMeals;

@Repository
public interface DayMealsRepository extends JpaRepository<DayMeals, Long> {
}
