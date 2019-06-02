package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.PathVariable;
import pl.wujekscho.dietplanner.entity.EntityModel;

import java.util.List;

public interface EntityController<T extends EntityModel> extends EntityModel {
    List<T> getAll();

    T getById(@PathVariable Long id);

    //todo skończyć interfejs
//    void save(T model);
//
//    void update(T model, Long id);
//
//    void remove(Long id);
}
