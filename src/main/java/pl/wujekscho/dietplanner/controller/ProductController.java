package pl.wujekscho.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.Product;
import pl.wujekscho.dietplanner.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "")
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.getOne(id);
    }
}
