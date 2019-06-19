package pl.wujekscho.dietplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wujekscho.dietplanner.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByName(String name);

    List<Product> findOrderByNameAsc();
}
