package pl.wujekscho.dietplanner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public User user(Principal user) {
        return userService.getByUsername(user.getName());
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/check/{username}")
    public boolean isAvailable(@PathVariable String username) {
        return userService.checkUsernameAvailability(username);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserWithDefaultRole(@RequestBody User user) {
        userService.addWithDefaultRole(user);
    }


}
