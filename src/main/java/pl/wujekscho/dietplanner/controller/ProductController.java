package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
