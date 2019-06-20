package pl.wujekscho.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "")
    public List<Product> getAll() {
        return productRepository.findAllByOrderByNameAsc();
    }

    @GetMapping(path = "/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.getOne(id);
    }

    @PostMapping(path = "")
    public void save(@RequestBody Product product) {
        productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
