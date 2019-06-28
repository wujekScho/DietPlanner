package pl.wujekscho.dietplanner.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.wujekscho.dietplanner.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(String role);
}
