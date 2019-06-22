package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wujekscho.dietplanner.entity.PlannedDay;

import java.util.List;

public interface PlannedDayRepository extends JpaRepository<PlannedDay, Long> {
    @Query("SELECT p FROM PlannedDay p WHERE p.user.id = :id AND p.mealsDate >= CURRENT_DATE ORDER BY p.mealsDate")
    List<PlannedDay> findAllByUserIdOrderByMealsDate(@Param("id") Long id);

}
