package pl.wujekscho.dietplanner.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.wujekscho.dietplanner.dto.WeightMeasurementDto;
import pl.wujekscho.dietplanner.entity.User;
import pl.wujekscho.dietplanner.entity.UserRole;
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
        user.setCaloriesNeeded(user.getCaloriesNeeded());
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

    public void addWeightMeasurement(WeightMeasurementDto weightMeasurementDto) {
        User user = userRepository.getOne(weightMeasurementDto.getUserId());
        LocalDate measurementDate = weightMeasurementDto.getMeasurementDate();
        Double weight = weightMeasurementDto.getWeight();
        Map<LocalDate, Double> weightOverTime = user.getWeightOverTime();
        if (weightOverTime.containsKey(measurementDate)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "not_unique_date");
        }
        weightOverTime.put(measurementDate, weight);
        user.setWeightOverTime(weightOverTime);
        userRepository.save(user);
    }

    public void editWeightMeasurement(WeightMeasurementDto weightMeasurementDto) {
        User user = userRepository.getOne(weightMeasurementDto.getUserId());
        Map<LocalDate, Double> weightOverTime = user.getWeightOverTime();
        weightOverTime.put(weightMeasurementDto.getMeasurementDate(), weightMeasurementDto.getWeight());
        user.setWeightOverTime(weightOverTime);
        userRepository.save(user);
    }

    public void deleteWeightMeasurement(WeightMeasurementDto weightMeasurementDto) {
        User user = userRepository.getOne(weightMeasurementDto.getUserId());
        Map<LocalDate, Double> weightOverTime = user.getWeightOverTime();
        weightOverTime.remove(weightMeasurementDto.getMeasurementDate());
        user.setWeightOverTime(weightOverTime);
        userRepository.save(user);
    }
}
