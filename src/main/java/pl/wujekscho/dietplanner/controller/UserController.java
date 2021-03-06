package pl.wujekscho.dietplanner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.wujekscho.dietplanner.dto.WeightMeasurementDto;
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

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserWithDefaultRole(@RequestBody User user) {
        userService.addWithDefaultRole(user);
    }

    @PostMapping(path = "/add-weight", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addWeightMeasurement(@RequestBody WeightMeasurementDto weightMeasurementDto) {
        userService.addWeightMeasurement(weightMeasurementDto);
    }

    @PostMapping(path = "/edit-weight", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editWeightMeasurement(@RequestBody WeightMeasurementDto weightMeasurementDto) {
        userService.editWeightMeasurement(weightMeasurementDto);
    }

    @PostMapping(path = "/delete-weight", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteWeightMeasurement(@RequestBody WeightMeasurementDto weightMeasurementDto) {
        userService.deleteWeightMeasurement(weightMeasurementDto);
    }


}
