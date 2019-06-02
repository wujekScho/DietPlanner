package pl.wujekscho.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.MealProduct;
import pl.wujekscho.dietplanner.repository.MealProductRepository;

import java.util.List;

@RestController
@RequestMapping("/meal_products")
public class MealProductController implements EntityController<MealProduct> {
    private final MealProductRepository mealProductRepository;

    @Autowired
    public MealProductController(MealProductRepository mealProductRepository) {
        this.mealProductRepository = mealProductRepository;
    }

    @Override
    @GetMapping
    public List<MealProduct> getAll() {
        return mealProductRepository.findAll();
    }

    @Override
    public MealProduct getById(Long id) {
        return mealProductRepository.getOne(id);
    }
}
