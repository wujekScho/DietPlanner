package pl.wujekscho.dietplanner.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.entity.UserRole;
import pl.wujekscho.dietplanner.model.WeightMeasurement;
import pl.wujekscho.dietplanner.repository.UserRepository;
import pl.wujekscho.dietplanner.repository.UserRoleRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserService {
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addWithDefaultRole(User user) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getById(Long id) {
        User user = userRepository.getOne(id);
        TreeMap<LocalDate, Double> sorted = new TreeMap<>();
        sorted.putAll(user.getWeightOverTime());
        user.setWeightOverTime(sorted);
        return user;

    }

    public boolean checkUsernameAvailability(String username) {
        User user = getByUsername(username);
        return user == null;
    }

    public void addWeightMeasurement(WeightMeasurement weightMeasurement) {
        User user = userRepository.getOne(weightMeasurement.getUserId());
        LocalDate measurementDate = weightMeasurement.getMeasurementDate();
        Double weight = weightMeasurement.getWeight();
        Map<LocalDate, Double> weightOverTime = user.getWeightOverTime();
        if (weightOverTime.containsKey(measurementDate)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "not_unique_date");
        }
        weightOverTime.put(measurementDate, weight);
        user.setWeightOverTime(weightOverTime);
        userRepository.save(user);
    }

    public void editWeightMeasurement(WeightMeasurement weightMeasurement) {
        User user = userRepository.getOne(weightMeasurement.getUserId());
        Map<LocalDate, Double> weightOverTime = user.getWeightOverTime();
        weightOverTime.put(weightMeasurement.getMeasurementDate(), weightMeasurement.getWeight());
        user.setWeightOverTime(weightOverTime);
        userRepository.save(user);
    }

    public void deleteWeightMeasurement(WeightMeasurement weightMeasurement) {
        User user = userRepository.getOne(weightMeasurement.getUserId());
        Map<LocalDate, Double> weightOverTime = user.getWeightOverTime();
        weightOverTime.remove(weightMeasurement.getMeasurementDate());
        user.setWeightOverTime(weightOverTime);
        userRepository.save(user);
    }
}
