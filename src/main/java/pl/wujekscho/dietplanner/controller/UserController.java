package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.service.UserService;

import java.security.Principal;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User user(Principal user) {
        return userService.getByUsername(user.getName());
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }


}
