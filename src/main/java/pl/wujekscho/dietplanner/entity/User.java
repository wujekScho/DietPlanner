package pl.wujekscho.dietplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<PlannedDay> plannedDays = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();
    @ElementCollection(targetClass = Double.class)
    @MapKeyColumn(name = "measurement_date")
    private Map<LocalDate, Double> weightOverTime = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
