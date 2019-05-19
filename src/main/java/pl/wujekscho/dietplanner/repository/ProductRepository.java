package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wujekscho.dietplanner.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
