package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wujekscho.dietplanner.entity.DayMeals;

import java.util.List;

@Repository
public interface DayMealsRepository extends JpaRepository<DayMeals, Long> {

    @Query("SELECT d FROM DayMeals d WHERE d.user.id=null OR d.user.id = :id")
    List<DayMeals> getAllByUserId(@Param("id") Long id);
}
