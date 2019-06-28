package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wujekscho.dietplanner.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
